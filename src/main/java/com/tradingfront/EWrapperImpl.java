package com.tradingfront;

import com.ib.client.*;

import java.util.Set;

public class EWrapperImpl implements EWrapper {

    //! [socket_declare]
    private EReaderSignal readerSignal;
    private EClientSocket clientSocket;
    protected int currentOrderId = -1;
    //! [socket_declare]

    //! [socket_init]
    public EWrapperImpl() {
        readerSignal = new EJavaSignal();
        clientSocket = new EClientSocket(this, readerSignal);
    }

    //! [socket_init]
    public EClientSocket getClient() {
        return clientSocket;
    }

    public EReaderSignal getSignal() {
        return readerSignal;
    }

    public int getCurrentOrderId() {
        return currentOrderId;
    }

    //! [tickprice]
    @Override
    public void tickPrice(int tickerId, int field, double price, int canAutoExecute) {
        System.out.println("Tick Price. Ticker Id:" + tickerId + ", Field: " + field + ", Price: " + price + ", CanAutoExecute: " + canAutoExecute);
    }
    //! [tickprice]

    //! [ticksize]
    @Override
    public void tickSize(int tickerId, int field, int size) {
        System.out.println("Tick Size. Ticker Id:" + tickerId + ", Field: " + field + ", Size: " + size);
    }
    //! [ticksize]

    //! [tickoptioncomputation]
    @Override
    public void tickOptionComputation(int tickerId, int field,
                                      double impliedVol, double delta, double optPrice,
                                      double pvDividend, double gamma, double vega, double theta,
                                      double undPrice) {
        System.out.println("TickOptionComputation. TickerId: " + tickerId + ", field: " + field + ", ImpliedVolatility: " + impliedVol + ", Delta: " + delta
                + ", OptionPrice: " + optPrice + ", pvDividend: " + pvDividend + ", Gamma: " + gamma + ", Vega: " + vega + ", Theta: " + theta + ", UnderlyingPrice: " + undPrice);
    }
    //! [tickoptioncomputation]

    //! [tickgeneric]
    @Override
    public void tickGeneric(int tickerId, int tickType, double value) {
        System.out.println("Tick Generic. Ticker Id:" + tickerId + ", Field: " + TickType.getField(tickType) + ", Value: " + value);
    }
    //! [tickgeneric]

    //! [tickstring]
    @Override
    public void tickString(int tickerId, int tickType, String value) {
        System.out.println("Tick string. Ticker Id:" + tickerId + ", Type: " + tickType + ", Value: " + value);
    }

    //! [tickstring]
    @Override
    public void tickEFP(int tickerId, int tickType, double basisPoints,
                        String formattedBasisPoints, double impliedFuture, int holdDays,
                        String futureLastTradeDate, double dividendImpact,
                        double dividendsToLastTradeDate) {
        System.out.println("TickEFP. " + tickerId + ", Type: " + tickType + ", BasisPoints: " + basisPoints + ", FormattedBasisPoints: " +
                formattedBasisPoints + ", ImpliedFuture: " + impliedFuture + ", HoldDays: " + holdDays + ", FutureLastTradeDate: " + futureLastTradeDate +
                ", DividendImpact: " + dividendImpact + ", DividendsToLastTradeDate: " + dividendsToLastTradeDate);
    }

    //! [orderstatus]
    @Override
    public void orderStatus(int orderId, String status, double filled,
                            double remaining, double avgFillPrice, int permId, int parentId,
                            double lastFillPrice, int clientId, String whyHeld) {
        System.out.println("OrderStatus. Id: " + orderId + ", Status: " + status + ", Filled:" + filled + ", Remaining: " + remaining
                + ", AvgFillPrice: " + avgFillPrice + ", PermId: " + permId + ", ParentId: " + parentId + ", LastFillPrice: " + lastFillPrice +
                ", ClientId: " + clientId + ", WhyHeld: " + whyHeld);

//        // 驱动程序名
//        String driver = "com.mysql.jdbc.Driver";
//        // URL指向要访问的数据库名mysql
//        String url = "jdbc:mysql://127.0.0.1:3306/mysql?characterEncoding=utf-8&useSSL=false";
//        // MySQL配置时的用户名
//        String user = "root";
//        // MySQL配置时的密码
//        String password = "fansen@1992";
//        if (status.equals("Filled")) {
//            try {
//                // 加载驱动程序
//                Class.forName(driver);
//                // 连接数据库
//                Connection conn = DriverManager.getConnection(url, user, password);
//                if (!conn.isClosed())
//                    System.out.println("Succeeded connecting to the Database!");
//                // statement用来执行SQL语句
//                Statement statement = conn.createStatement();
//                // 要执行的SQL语句
//                String sql = "update ibtest set id=" + (orderId + 1) +
//                        ", quantity=" + filled +
//                        ", secType ='STK', currency='HKD'," +
//                        " exchange='SEHK', symbol='6288', action='BUY', orderType='MKT', account='DU525700', status='" + status +
//                        "' WHERE 1";
//                // 结果集
//                System.out.println("executeUpdate = " + statement.executeUpdate(sql));
//            } catch (ClassNotFoundException e) {
//                System.out.println("Sorry,can`t find the Driver!");
//                e.printStackTrace();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//        }
    }
    //! [orderstatus]

    //! [openorder]
    @Override
    public void openOrder(int orderId, Contract contract, Order order, OrderState orderState) {
        System.out.println("OpenOrder. ID: " + orderId + ", " + contract.symbol() + ", " + contract.secType() + " @ " + contract.exchange() + ": " +
                order.action() + ", " + order.orderType() + " " + order.totalQuantity() + ", " + orderState.status() + ", conid = " + contract.conid());
    }
    //! [openorder]

    //! [openorderend]
    @Override
    public void openOrderEnd() {
        System.out.println("OpenOrderEnd");
    }
    //! [openorderend]

    @Override
    public void updateAccountTime(String timeStamp) {
        System.out.println("UpdateAccountTime. Time: " + timeStamp + "\n");
    }

    @Override
    public void updateAccountValue(String key, String value, String currency, String accountName) {
        System.out.println("UpdateAccountValue. Key: " + key + ", Value: " + value + ", Currency: " + currency + ", AccountName: " + accountName);
    }

    @Override
    public void updatePortfolio(Contract contract, double position, double marketPrice, double marketValue, double averageCost,
                                double unrealizedPNL, double realizedPNL, String accountName) {
        System.out.println("UpdatePortfolio. " + contract.conid() + ", " + contract.symbol() + ", " + contract.secType() + " @ " + contract.exchange()
                + ": Position: " + position + ", MarketPrice: " + marketPrice + ", MarketValue: " + marketValue + ", AverageCost: " + averageCost
                + ", UnrealisedPNL: " + unrealizedPNL + ", RealisedPNL: " + realizedPNL + ", AccountName: " + accountName);
    }


    //! [accountdownloadend]
    @Override
    public void accountDownloadEnd(String accountName) {
        System.out.println("Account download finished: " + accountName + "\n");
    }
    //! [accountdownloadend]

    @Override
    public void nextValidId(int orderId) {
        System.out.println("Next Valid Id: [" + orderId + "]");
        currentOrderId = orderId;
    }

    //! [contractdetails]
    @Override
    public void contractDetails(int reqId, ContractDetails contractDetails) {
        System.out.println("ContractDetails. ReqId: [" + reqId + "] - [" + contractDetails.contract().symbol() + "], [" + contractDetails.contract().secType() + "], ConId: [" + contractDetails.contract().conid() + "] @ [" + contractDetails.contract().exchange() + "]");
        System.out.println("contractMsg: " + contractMsg(contractDetails.contract()));
        System.out.println("contractDetailsMsg: " + contractDetailsMsg(contractDetails));
    }

    private static String contractDetailsMsg(ContractDetails contractDetails) {
        String msg = "marketName = " + contractDetails.marketName() + "\n"
                + "minTick = " + contractDetails.minTick() + "\n"
                + "price magnifier = " + contractDetails.priceMagnifier() + "\n"
                + "orderTypes = " + contractDetails.orderTypes() + "\n"
                + "validExchanges = " + contractDetails.validExchanges() + "\n"
                + "underConId = " + contractDetails.underConid() + "\n"
                + "longName = " + contractDetails.longName() + "\n"
                + "contractMonth = " + contractDetails.contractMonth() + "\n"
                + "industry = " + contractDetails.industry() + "\n"
                + "category = " + contractDetails.category() + "\n"
                + "subcategory = " + contractDetails.subcategory() + "\n"
                + "timeZoneId = " + contractDetails.timeZoneId() + "\n"
                + "tradingHours = " + contractDetails.tradingHours() + "\n"
                + "liquidHours = " + contractDetails.liquidHours() + "\n"
                + "evRule = " + contractDetails.evRule() + "\n"
                + "evMultiplier = " + contractDetails.evMultiplier();
        return msg;
    }

    static public String contractMsg(Contract contract) {
        String msg = "conid = " + contract.conid() + "\n"
                + "symbol = " + contract.symbol() + "\n"
                + "secType = " + contract.getSecType() + "\n"
                + "lastTradeDate = " + contract.lastTradeDateOrContractMonth() + "\n"
                + "strike = " + contract.strike() + "\n"
                + "right = " + contract.getRight() + "\n"
                + "multiplier = " + contract.multiplier() + "\n"
                + "exchange = " + contract.exchange() + "\n"
                + "primaryExch = " + contract.primaryExch() + "\n"
                + "currency = " + contract.currency() + "\n"
                + "localSymbol = " + contract.localSymbol() + "\n"
                + "tradingClass = " + contract.tradingClass() + "\n";
        return msg;
    }

    //! [contractdetails]
    @Override
    public void bondContractDetails(int reqId, ContractDetails contractDetails) {
        System.out.println("bondContractDetails");
    }

    //! [contractdetailsend]
    @Override
    public void contractDetailsEnd(int reqId) {
        System.out.println("ContractDetailsEnd. " + reqId + "\n");
    }
    //! [contractdetailsend]

    //! [execdetails]
    @Override
    public void execDetails(int reqId, Contract contract, Execution execution) {
        System.out.println("ExecDetails. " + reqId + " - [" + contract.symbol() + "], [" + contract.secType() + "], [" + contract.currency() + "], [" + execution.execId() + "], [" + execution.orderId() + "], [" + execution.shares() + "]");
    }
    //! [execdetails]

    //! [execdetailsend]
    @Override
    public void execDetailsEnd(int reqId) {
        System.out.println("ExecDetailsEnd. " + reqId + "\n");
    }
    //! [execdetailsend]

    //! [updatemktdepth]
    @Override
    public void updateMktDepth(int tickerId, int position, int operation,
                               int side, double price, int size) {
        System.out.println("UpdateMarketDepth. " + tickerId + " - Position: " + position + ", Operation: " + operation + ", Side: " + side + ", Price: " + price + ", Size: " + size + "");
    }

    //! [updatemktdepth]
    @Override
    public void updateMktDepthL2(int tickerId, int position,
                                 String marketMaker, int operation, int side, double price, int size) {
        System.out.println("updateMktDepthL2");
    }

    //! [updatenewsbulletin]
    @Override
    public void updateNewsBulletin(int msgId, int msgType, String message,
                                   String origExchange) {
        System.out.println("News Bulletins. " + msgId + " - Type: " + msgType + ", Message: " + message + ", Exchange of Origin: " + origExchange + "\n");
    }
    //! [updatenewsbulletin]

    @Override
    public void managedAccounts(String accountsList) {
        System.out.println("EWrapperImpl managedAccounts Account list: " + accountsList);
    }

    //! [receivefa]
    @Override
    public void receiveFA(int faDataType, String xml) {
        System.out.println("Receing FA: " + faDataType + " - " + xml);
    }
    //! [receivefa]

    //! [historicaldata]
    @Override
    public void historicalData(int reqId, String date, double open,
                               double high, double low, double close, int volume, int count,
                               double WAP, boolean hasGaps) {
        System.out.println("HistoricalData. " + reqId + " - Date: " + date + ", Open: " + open + ", High: " + high + ", Low: " + low + ", Close: " + close + ", Volume: " + volume + ", Count: " + count + ", WAP: " + WAP + ", HasGaps: " + hasGaps);
    }
    //! [historicaldata]

    //! [scannerparameters]
    @Override
    public void scannerParameters(String xml) {
        System.out.println("ScannerParameters. " + xml + "\n");
    }
    //! [scannerparameters]

    //! [scannerdata]
    @Override
    public void scannerData(int reqId, int rank,
                            ContractDetails contractDetails, String distance, String benchmark,
                            String projection, String legsStr) {
        System.out.println("ScannerData. " + reqId + " - Rank: " + rank + ", Symbol: " + contractDetails.contract().symbol() + ", SecType: " + contractDetails.contract().secType() + ", Currency: " + contractDetails.contract().currency()
                + ", Distance: " + distance + ", Benchmark: " + benchmark + ", Projection: " + projection + ", Legs String: " + legsStr);
    }
    //! [scannerdata]

    //! [scannerdataend]
    @Override
    public void scannerDataEnd(int reqId) {
        System.out.println("ScannerDataEnd. " + reqId);
    }
    //! [scannerdataend]

    //! [realtimebar]
    @Override
    public void realtimeBar(int reqId, long time, double open, double high,
                            double low, double close, long volume, double wap, int count) {
        System.out.println("RealTimeBars. " + reqId + " - Time: " + time + ", Open: " + open + ", High: " + high + ", Low: " + low + ", Close: " + close + ", Volume: " + volume + ", Count: " + count + ", WAP: " + wap);
    }

    //! [realtimebar]
    @Override
    public void currentTime(long time) {
        System.out.println("currentTime = " + EWrapperMsgGenerator.currentTime(time));
    }

    //! [fundamentaldata]
    @Override
    public void fundamentalData(int reqId, String data) {
        System.out.println("FundamentalData. ReqId: [" + reqId + "] - Data: [" + data + "]");
    }

    //! [fundamentaldata]
    @Override
    public void deltaNeutralValidation(int reqId, DeltaNeutralContract underComp) {
        System.out.println("deltaNeutralValidation");
    }

    //! [ticksnapshotend]
    @Override
    public void tickSnapshotEnd(int reqId) {
        System.out.println("TickSnapshotEnd: " + reqId);
    }
    //! [ticksnapshotend]

    //! [marketdatatype]
    @Override
    public void marketDataType(int reqId, int marketDataType) {
        System.out.println("MarketDataType. [" + reqId + "], Type: [" + marketDataType + "]\n");
    }
    //! [marketdatatype]

    //! [commissionreport]
    @Override
    public void commissionReport(CommissionReport commissionReport) {
        System.out.println("CommissionReport. [" + commissionReport.m_execId + "] - [" + commissionReport.m_commission + "] [" + commissionReport.m_currency + "] RPNL [" + commissionReport.m_realizedPNL + "]");
    }
    //! [commissionreport]

    //! [position]
    @Override
    public void position(String account, Contract contract, double pos,
                         double avgCost) {
        System.out.println("Position. " + account + " - Symbol: " + contract.symbol() + ", SecType: " + contract.secType() + ", Currency: " + contract.currency() + ", Position: " + pos + ", Avg cost: " + avgCost);
    }
    //! [position]

    //! [positionend]
    @Override
    public void positionEnd() {
        System.out.println("PositionEnd \n");
    }
    //! [positionend]

    //! [accountsummary]
    @Override
    public void accountSummary(int reqId, String account, String tag, String value, String currency) {
        System.out.println("Acct Summary. ReqId: " + reqId + ", Acct: " + account + ", Tag: " + tag + ", Value: " + value + ", Currency: " + currency);
    }
    //! [accountsummary]

    //! [accountsummaryend]
    @Override
    public void accountSummaryEnd(int reqId) {
        System.out.println("AccountSummaryEnd. Req Id: " + reqId + "\n");
    }

    //! [accountsummaryend]
    @Override
    public void verifyMessageAPI(String apiData) {
        System.out.println("verifyMessageAPI");
    }

    @Override
    public void verifyCompleted(boolean isSuccessful, String errorText) {
        System.out.println("verifyCompleted");
    }

    @Override
    public void verifyAndAuthMessageAPI(String apiData, String xyzChallange) {
        System.out.println("verifyAndAuthMessageAPI");
    }

    @Override
    public void verifyAndAuthCompleted(boolean isSuccessful, String errorText) {
        System.out.println("verifyAndAuthCompleted");
    }

    //! [displaygrouplist]
    @Override
    public void displayGroupList(int reqId, String groups) {
        System.out.println("Display Group List. ReqId: " + reqId + ", Groups: " + groups + "\n");
    }
    //! [displaygrouplist]

    //! [displaygroupupdated]
    @Override
    public void displayGroupUpdated(int reqId, String contractInfo) {
        System.out.println("Display Group Updated. ReqId: " + reqId + ", Contract info: " + contractInfo + "\n");
    }

    //! [displaygroupupdated]
    @Override
    public void error(Exception e) {
        System.out.println("Exception: " + e.getMessage());
    }

    @Override
    public void error(String str) {
        System.out.println("Error STR");
    }

    //! [error]
    @Override
    public void error(int id, int errorCode, String errorMsg) {
        System.out.println("Error. Id: " + id + ", Code: " + errorCode + ", Msg: " + errorMsg + "\n");
    }

    //! [error]
    @Override
    public void connectionClosed() {
        System.out.println("Connection closed");
    }

    //! [connectack]
    @Override
    public void connectAck() {
        if (clientSocket.isAsyncEConnect()) {
            System.out.println("Acknowledging connection");
            clientSocket.startAPI();
        }
    }
    //! [connectack]

    //! [positionmulti]
    @Override
    public void positionMulti(int reqId, String account, String modelCode,
                              Contract contract, double pos, double avgCost) {
        System.out.println("Position Multi. Request: " + reqId + ", Account: " + account + ", ModelCode: " + modelCode + ", Symbol: " + contract.symbol() + ", SecType: " + contract.secType() + ", Currency: " + contract.currency() + ", Position: " + pos + ", Avg cost: " + avgCost + "\n");
    }
    //! [positionmulti]

    //! [positionmultiend]
    @Override
    public void positionMultiEnd(int reqId) {
        System.out.println("Position Multi End. Request: " + reqId + "\n");
    }
    //! [positionmultiend]

    //! [accountupdatemulti]
    @Override
    public void accountUpdateMulti(int reqId, String account, String modelCode,
                                   String key, String value, String currency) {
        System.out.println("Account Update Multi. Request: " + reqId + ", Account: " + account + ", ModelCode: " + modelCode + ", Key: " + key + ", Value: " + value + ", Currency: " + currency + "\n");
    }
    //! [accountupdatemulti]

    //! [accountupdatemultiend]
    @Override
    public void accountUpdateMultiEnd(int reqId) {
        System.out.println("Account Update Multi End. Request: " + reqId + "\n");
    }
    //! [accountupdatemultiend]

    //! [securityDefinitionOptionParameter]
    @Override
    public void securityDefinitionOptionalParameter(int reqId, String exchange,
                                                    int underlyingConId, String tradingClass, String multiplier,
                                                    Set<String> expirations, Set<Double> strikes) {
        System.out.println("Security Definition Optional Parameter. Request: " + reqId + ", Trading Class: " + tradingClass + ", Multiplier: " + multiplier + " \n");
    }

    //! [securityDefinitionOptionParameter]
    @Override
    public void securityDefinitionOptionalParameterEnd(int reqId) {
        // TODO Auto-generated method stub

    }

    @Override
    public void softDollarTiers(int reqId, SoftDollarTier[] tiers) {
        for (SoftDollarTier tier : tiers) {
            System.out.print("tier: " + tier + ", ");
        }

        System.out.println();
    }

}
