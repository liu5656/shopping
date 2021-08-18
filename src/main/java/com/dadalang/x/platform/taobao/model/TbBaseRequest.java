package com.dadalang.x.platform.taobao.model;

import com.taobao.api.ApiRuleException;
import com.taobao.api.TaobaoRequest;
import com.taobao.api.TaobaoResponse;

import java.util.Map;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/8/17 11:53 上午
 * @desc
 */
public class TbBaseRequest<T extends TaobaoResponse> implements TaobaoRequest {

    protected Long timestamp;
    protected Map<String, String> header;

    @Override
    public String getApiMethodName() {
        return null;
    }

    @Override
    public String getTopContentType() {
        return null;
    }

    @Override
    public void setTopContentType(String s) {

    }

    @Override
    public String getTopResponseType() {
        return null;
    }

    @Override
    public void setTopResponseType(String s) {

    }

    @Override
    public String getTopApiVersion() {
        return null;
    }

    @Override
    public void setTopApiVersion(String s) {

    }

    @Override
    public String getTopApiFormat() {
        return null;
    }

    @Override
    public void setTopApiFormat(String s) {

    }

    @Override
    public String getTopApiCallType() {
        return null;
    }

    @Override
    public void setTopApiCallType(String s) {

    }

    @Override
    public String getTopHttpMethod() {
        return null;
    }

    @Override
    public void setTopHttpMethod(String s) {

    }

    @Override
    public Map<String, String> getTextParams() {
        return null;
    }

    @Override
    public Long getTimestamp() {
        return timestamp == null ? 0 : timestamp;
    }

    @Override
    public String getTargetAppKey() {
        return null;
    }

    @Override
    public Class getResponseClass() {
        return null;
    }

    @Override
    public Map<String, String> getHeaderMap() {
        return null;
    }

    @Override
    public void check() throws ApiRuleException {

    }

    @Override
    public String getBatchApiSession() {
        return null;
    }

    @Override
    public void setBatchApiSession(String s) {

    }

    @Override
    public int getBatchApiOrder() {
        return 0;
    }

    @Override
    public void setBatchApiOrder(int i) {

    }
}
