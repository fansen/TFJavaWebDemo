package com.tradingfront.Setting;

import com.ib.client.EClientSocket;
import com.ib.client.EReader;
import com.ib.client.EReaderSignal;
import com.tradingfront.EWrapperImpl;

/**
 * Created by dell on 2017/1/12.
 */
public class SettingData {

    public static boolean m_disconnectInProgress = false;

    public static EWrapperImpl wrapper = new EWrapperImpl();
    public static EClientSocket m_client = wrapper.getClient();
    public static EReaderSignal m_signal = wrapper.getSignal();
    public static EReader m_reader;
}
