package com.tradingfront;

import com.ib.client.EReader;
import com.ib.client.EWrapperMsgGenerator;
import com.tradingfront.Setting.SettingData;

/**
 * Created by dell on 2017/1/12.
 *
 */
public class Utils {

    public static void Connect(String m_reqIp, int m_reqPort, int m_reqClientId) {

        // connect to TWS
        SettingData.m_disconnectInProgress = false;

        SettingData.m_client.eConnect(m_reqIp, m_reqPort, m_reqClientId);
        if (SettingData.m_client.isConnected()) {
            System.out.println("Connected to Tws server version " +
                    SettingData.m_client.serverVersion() + " at " +
                    SettingData.m_client.TwsConnectionTime());
        }

        SettingData.m_reader = new EReader(SettingData.m_client, SettingData.m_signal);
        SettingData.m_reader.start();
        System.out.println("connect");

        new Thread(() -> {
            System.out.println("processMessages START");
            processMessages();
            System.out.println("processMessages END");
        }).start();
    }

    public static void Disconnect() {
        System.out.println("Disconnect");
        SettingData.m_disconnectInProgress = true;
        SettingData.m_client.eDisconnect();
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

    public static void onReqAcctData(boolean subscribe, String acctCode) {
        SettingData.m_client.reqAccountUpdates(subscribe, acctCode);
    }


}
