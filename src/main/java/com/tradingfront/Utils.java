package com.tradingfront;

import com.ib.client.Contract;
import com.ib.client.EReader;
import com.ib.client.EWrapperMsgGenerator;
import com.ib.client.Order;
import com.tradingfront.Setting.SettingData;

/**
 * Created by dell on 2017/1/12.
 *
 */
public class Utils {

    public static String Connect(String m_reqIp, int m_reqPort, int m_reqClientId) {

        String ConnectResult;
        // connect to TWS
        SettingData.m_disconnectInProgress = false;

        SettingData.m_client.eConnect(m_reqIp, m_reqPort, m_reqClientId);
        if (SettingData.m_client.isConnected()) {
            System.out.println("Connected to Tws server version " +
                    SettingData.m_client.serverVersion() + " at " +
                    SettingData.m_client.TwsConnectionTime());
            ConnectResult = "Connect succeeded!";
        } else {
            ConnectResult = "Connect failed!";
        }

        SettingData.m_reader = new EReader(SettingData.m_client, SettingData.m_signal);
        SettingData.m_reader.start();
        System.out.println("connect");

        new Thread(() -> {
            System.out.println("processMessages START");
            processMessages();
            System.out.println("processMessages END");
        }).start();
        return ConnectResult;
    }

    public static String Disconnect() {
        String DisconnectResult;

        System.out.println("Disconnect");
        SettingData.m_disconnectInProgress = true;
        SettingData.m_client.eDisconnect();
        if (SettingData.m_client.isConnected()) {
            DisconnectResult = "Disconnect failed!";
        } else {
            DisconnectResult = "Disconnect succeeded!";
        }
        return DisconnectResult;
    }

    private static void processMessages() {
        while (SettingData.m_client.isConnected()) {
            SettingData.m_signal.waitForSignal();
            try {
                SettingData.m_reader.processMsgs();
            } catch (Exception e) {
                error(e);
            }
        }
    }

    public static void error(Exception ex) {
        // do not report exceptions if we initiated disconnect
        if (!SettingData.m_disconnectInProgress) {
            String msg = EWrapperMsgGenerator.error(ex);
            System.out.println("ERROR: " + msg);
        }
    }

    public static int getCurrentOrderId() {
        return SettingData.wrapper.getCurrentOrderId();
    }

    public static void onReqAcctData(boolean subscribe, String acctCode) {
        System.out.println("reqAccount acctCode = " + acctCode);
        SettingData.m_client.reqAccountUpdates(subscribe, acctCode);
    }

    public static void onReqMktData(int orderId, Contract contract) {
        System.out.println("onReqMktData orderId = " + orderId);
        SettingData.m_client.reqMktData(orderId, contract, "", false, null);
    }

    public static void onCancelMktData(int orderId) {
        System.out.println("onCancelMktData orderId = " + orderId);
        SettingData.m_client.cancelMktData(orderId);
    }

    public static void onReqMktDepth(int orderId, Contract contract) {
        System.out.println("onReqMktDepth orderId = " + orderId);
        SettingData.m_client.reqMktDepth(orderId, contract, 20, null);
    }

    public static void onCancelMktDepth(int orderId) {
        System.out.println("onCancelMktDepth orderId = " + orderId);
        SettingData.m_client.cancelMktDepth(orderId);
    }

    public static void singleMarketOrder(String account, String action, double quantity) {
        Order order = new Order();
        order.account(account);
        order.action(action);
        order.orderType("MKT");
        order.totalQuantity(quantity);

        Contract contract = new Contract();
        contract.symbol("6288");
        contract.secType("STK");
        contract.currency("HKD");
        contract.exchange("SEHK");
        SettingData.m_client.placeOrder(1000, contract, order);
    }


}
