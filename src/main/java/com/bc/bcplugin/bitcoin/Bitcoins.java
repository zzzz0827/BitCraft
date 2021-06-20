package com.bc.bcplugin.bitcoin;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * 빗썸 open API를 불러옵니다.
 */
public class Bitcoins {

    private static final String API_URL = "https://api.bithumb.com/public/ticker/ALL_KRW";
    String result;
    URL url;
    BufferedReader bf;

    JSONParser jsonParser;
    JSONObject jsonObject;
    JSONObject coinData;
    JSONObject coinInfo;

    String bitcoin;
    Object openingPrice;
    Object closingPrice;
    Object minPrice;
    Object maxPrice;
    Object unitTraded;
    Object accTradeValue;
    Object prevClosingPrice;
    Object unitsTraded24H;
    Object accTradeValue24H;
    Object fluctate24H;
    Object fluctateRate_24H;

    public Bitcoins(String bitcoin) {

        try {
            url = new URL(API_URL);
            bf = new BufferedReader(
                    new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
            result = bf.readLine();

            jsonParser = new JSONParser();
            jsonObject = (JSONObject) jsonParser.parse(result);
            coinData = (JSONObject) jsonObject.get("data");
            coinInfo = (JSONObject) coinData.get(bitcoin);

            setBitcoin(bitcoin);                                        // 비트코인 종류
            setOpeningPrice(coinInfo.get("opening_price"));             // 시가 00시 기준
            setClosingPrice(coinInfo.get("closing_price"));             // 종가 00시 기준 (현재가)
            setMinPrice(coinInfo.get("min_price"));                     // 저가 00시 기준
            setMaxPrice(coinInfo.get("max_price"));                     // 고가 00시 기준
            setUnitTraded(coinInfo.get("units_traded"));                // 거래량 00시 기준
            setAccTradeValue(coinInfo.get("acc_trade_value"));          // 거래 금액 00시 기준
            setPrevClosingPrice(coinInfo.get("prev_closing_price"));    // 전일 종가
            setUnitsTraded24H(coinInfo.get("units_traded_24H"));        // 최근 24시 거래량
            setAccTradeValue24H(coinInfo.get("acc_trade_value_24H"));   // 최근 24시 거래금액
            setFluctate24H(coinInfo.get("fluctate_24H"));               // 최근 24시 변동가
            setFluctateRate_24H(coinInfo.get("fluctate_rate_24H"));    // 최근 24시 변동률

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return "§6============[§e " + getBitcoin() +" §6]============\n" +
                getOpeningPrice() + "\n" +
                "§b현재가 §e: " + getClosingPrice() + "\n" +
                getMinPrice() + "\n" +
                getMaxPrice() + "\n" +
                getUnitTraded() + "\n" +
                getAccTradeValue() + "\n" +
                getPrevClosingPrice() + "\n" +
                getUnitsTraded24H() + "\n" +
                getAccTradeValue24H() + "\n" +
                getFluctate24H() + "\n" +
                getFluctateRate_24H() + "\n" +
                "§b환율 §e: KRW";
    }

    public String getBitcoin() {
        return bitcoin;
    }

    public void setBitcoin(String bitcoin) {
        this.bitcoin = bitcoin;
    }

    public Object getOpeningPrice() {
        return "§b시가 §e: " + openingPrice;
    }

    public void setOpeningPrice(Object openingPrice) {
        this.openingPrice = openingPrice;
    }

    public Object getClosingPrice() {
        return closingPrice;
    }

    public void setClosingPrice(Object closingPrice) {
        this.closingPrice = closingPrice;
    }

    public Object getMinPrice() {
        return "§b저가 §e: " + minPrice;
    }

    public void setMinPrice(Object minPrice) {
        this.minPrice = minPrice;
    }

    public Object getMaxPrice() {
        return "§b고가 §e: " + maxPrice;
    }

    public void setMaxPrice(Object maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Object getUnitTraded() {
        return "§b거래량 §e: " + unitTraded;
    }

    public void setUnitTraded(Object unitTraded) {
        this.unitTraded = unitTraded;
    }

    public Object getAccTradeValue() {
        return "§b거래금액 §e: " + accTradeValue;
    }

    public void setAccTradeValue(Object accTradeValue) {
        this.accTradeValue = accTradeValue;
    }

    public Object getPrevClosingPrice() {
        return "§b전일종가 §e: " + prevClosingPrice;
    }

    public void setPrevClosingPrice(Object prevClosingPrice) {
        this.prevClosingPrice = prevClosingPrice;
    }

    public Object getUnitsTraded24H() {
        return "§b최근 24시 거래량 §e: " + unitsTraded24H;
    }

    public void setUnitsTraded24H(Object unitsTraded24H) {
        this.unitsTraded24H = unitsTraded24H;
    }

    public Object getAccTradeValue24H() {
        return "§b최근 24시 거래금액 §e: " + accTradeValue24H;
    }

    public void setAccTradeValue24H(Object accTradeValue24H) {
        this.accTradeValue24H = accTradeValue24H;
    }

    public Object getFluctate24H() {
        return "§b최근 24시 변동가 §e: " + fluctate24H;
    }

    public void setFluctate24H(Object fluctate24H) {
        this.fluctate24H = fluctate24H;
    }

    public Object getFluctateRate_24H() {
        return "§b최근 24시 변동률 §e: " + fluctateRate_24H;
    }

    public void setFluctateRate_24H(Object fluctateRate_24H) {
        this.fluctateRate_24H = fluctateRate_24H;
    }

}

