package com.payease.wallet.entity.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TBankBaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TBankBaseExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andBankNameIsNull() {
            addCriterion("bank_name is null");
            return (Criteria) this;
        }

        public Criteria andBankNameIsNotNull() {
            addCriterion("bank_name is not null");
            return (Criteria) this;
        }

        public Criteria andBankNameEqualTo(String value) {
            addCriterion("bank_name =", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotEqualTo(String value) {
            addCriterion("bank_name <>", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameGreaterThan(String value) {
            addCriterion("bank_name >", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameGreaterThanOrEqualTo(String value) {
            addCriterion("bank_name >=", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLessThan(String value) {
            addCriterion("bank_name <", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLessThanOrEqualTo(String value) {
            addCriterion("bank_name <=", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLike(String value) {
            addCriterion("bank_name like", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotLike(String value) {
            addCriterion("bank_name not like", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameIn(List<String> values) {
            addCriterion("bank_name in", values, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotIn(List<String> values) {
            addCriterion("bank_name not in", values, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameBetween(String value1, String value2) {
            addCriterion("bank_name between", value1, value2, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotBetween(String value1, String value2) {
            addCriterion("bank_name not between", value1, value2, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankLogoUrlIsNull() {
            addCriterion("bank_logo_url is null");
            return (Criteria) this;
        }

        public Criteria andBankLogoUrlIsNotNull() {
            addCriterion("bank_logo_url is not null");
            return (Criteria) this;
        }

        public Criteria andBankLogoUrlEqualTo(String value) {
            addCriterion("bank_logo_url =", value, "bankLogoUrl");
            return (Criteria) this;
        }

        public Criteria andBankLogoUrlNotEqualTo(String value) {
            addCriterion("bank_logo_url <>", value, "bankLogoUrl");
            return (Criteria) this;
        }

        public Criteria andBankLogoUrlGreaterThan(String value) {
            addCriterion("bank_logo_url >", value, "bankLogoUrl");
            return (Criteria) this;
        }

        public Criteria andBankLogoUrlGreaterThanOrEqualTo(String value) {
            addCriterion("bank_logo_url >=", value, "bankLogoUrl");
            return (Criteria) this;
        }

        public Criteria andBankLogoUrlLessThan(String value) {
            addCriterion("bank_logo_url <", value, "bankLogoUrl");
            return (Criteria) this;
        }

        public Criteria andBankLogoUrlLessThanOrEqualTo(String value) {
            addCriterion("bank_logo_url <=", value, "bankLogoUrl");
            return (Criteria) this;
        }

        public Criteria andBankLogoUrlLike(String value) {
            addCriterion("bank_logo_url like", value, "bankLogoUrl");
            return (Criteria) this;
        }

        public Criteria andBankLogoUrlNotLike(String value) {
            addCriterion("bank_logo_url not like", value, "bankLogoUrl");
            return (Criteria) this;
        }

        public Criteria andBankLogoUrlIn(List<String> values) {
            addCriterion("bank_logo_url in", values, "bankLogoUrl");
            return (Criteria) this;
        }

        public Criteria andBankLogoUrlNotIn(List<String> values) {
            addCriterion("bank_logo_url not in", values, "bankLogoUrl");
            return (Criteria) this;
        }

        public Criteria andBankLogoUrlBetween(String value1, String value2) {
            addCriterion("bank_logo_url between", value1, value2, "bankLogoUrl");
            return (Criteria) this;
        }

        public Criteria andBankLogoUrlNotBetween(String value1, String value2) {
            addCriterion("bank_logo_url not between", value1, value2, "bankLogoUrl");
            return (Criteria) this;
        }

        public Criteria andBankLogoNotColorUrlIsNull() {
            addCriterion("bank_logo_not_color_url is null");
            return (Criteria) this;
        }

        public Criteria andBankLogoNotColorUrlIsNotNull() {
            addCriterion("bank_logo_not_color_url is not null");
            return (Criteria) this;
        }

        public Criteria andBankLogoNotColorUrlEqualTo(String value) {
            addCriterion("bank_logo_not_color_url =", value, "bankLogoNotColorUrl");
            return (Criteria) this;
        }

        public Criteria andBankLogoNotColorUrlNotEqualTo(String value) {
            addCriterion("bank_logo_not_color_url <>", value, "bankLogoNotColorUrl");
            return (Criteria) this;
        }

        public Criteria andBankLogoNotColorUrlGreaterThan(String value) {
            addCriterion("bank_logo_not_color_url >", value, "bankLogoNotColorUrl");
            return (Criteria) this;
        }

        public Criteria andBankLogoNotColorUrlGreaterThanOrEqualTo(String value) {
            addCriterion("bank_logo_not_color_url >=", value, "bankLogoNotColorUrl");
            return (Criteria) this;
        }

        public Criteria andBankLogoNotColorUrlLessThan(String value) {
            addCriterion("bank_logo_not_color_url <", value, "bankLogoNotColorUrl");
            return (Criteria) this;
        }

        public Criteria andBankLogoNotColorUrlLessThanOrEqualTo(String value) {
            addCriterion("bank_logo_not_color_url <=", value, "bankLogoNotColorUrl");
            return (Criteria) this;
        }

        public Criteria andBankLogoNotColorUrlLike(String value) {
            addCriterion("bank_logo_not_color_url like", value, "bankLogoNotColorUrl");
            return (Criteria) this;
        }

        public Criteria andBankLogoNotColorUrlNotLike(String value) {
            addCriterion("bank_logo_not_color_url not like", value, "bankLogoNotColorUrl");
            return (Criteria) this;
        }

        public Criteria andBankLogoNotColorUrlIn(List<String> values) {
            addCriterion("bank_logo_not_color_url in", values, "bankLogoNotColorUrl");
            return (Criteria) this;
        }

        public Criteria andBankLogoNotColorUrlNotIn(List<String> values) {
            addCriterion("bank_logo_not_color_url not in", values, "bankLogoNotColorUrl");
            return (Criteria) this;
        }

        public Criteria andBankLogoNotColorUrlBetween(String value1, String value2) {
            addCriterion("bank_logo_not_color_url between", value1, value2, "bankLogoNotColorUrl");
            return (Criteria) this;
        }

        public Criteria andBankLogoNotColorUrlNotBetween(String value1, String value2) {
            addCriterion("bank_logo_not_color_url not between", value1, value2, "bankLogoNotColorUrl");
            return (Criteria) this;
        }

        public Criteria andBankBgColorIsNull() {
            addCriterion("bank_bg_color is null");
            return (Criteria) this;
        }

        public Criteria andBankBgColorIsNotNull() {
            addCriterion("bank_bg_color is not null");
            return (Criteria) this;
        }

        public Criteria andBankBgColorEqualTo(String value) {
            addCriterion("bank_bg_color =", value, "bankBgColor");
            return (Criteria) this;
        }

        public Criteria andBankBgColorNotEqualTo(String value) {
            addCriterion("bank_bg_color <>", value, "bankBgColor");
            return (Criteria) this;
        }

        public Criteria andBankBgColorGreaterThan(String value) {
            addCriterion("bank_bg_color >", value, "bankBgColor");
            return (Criteria) this;
        }

        public Criteria andBankBgColorGreaterThanOrEqualTo(String value) {
            addCriterion("bank_bg_color >=", value, "bankBgColor");
            return (Criteria) this;
        }

        public Criteria andBankBgColorLessThan(String value) {
            addCriterion("bank_bg_color <", value, "bankBgColor");
            return (Criteria) this;
        }

        public Criteria andBankBgColorLessThanOrEqualTo(String value) {
            addCriterion("bank_bg_color <=", value, "bankBgColor");
            return (Criteria) this;
        }

        public Criteria andBankBgColorLike(String value) {
            addCriterion("bank_bg_color like", value, "bankBgColor");
            return (Criteria) this;
        }

        public Criteria andBankBgColorNotLike(String value) {
            addCriterion("bank_bg_color not like", value, "bankBgColor");
            return (Criteria) this;
        }

        public Criteria andBankBgColorIn(List<String> values) {
            addCriterion("bank_bg_color in", values, "bankBgColor");
            return (Criteria) this;
        }

        public Criteria andBankBgColorNotIn(List<String> values) {
            addCriterion("bank_bg_color not in", values, "bankBgColor");
            return (Criteria) this;
        }

        public Criteria andBankBgColorBetween(String value1, String value2) {
            addCriterion("bank_bg_color between", value1, value2, "bankBgColor");
            return (Criteria) this;
        }

        public Criteria andBankBgColorNotBetween(String value1, String value2) {
            addCriterion("bank_bg_color not between", value1, value2, "bankBgColor");
            return (Criteria) this;
        }

        public Criteria andBankCodeIsNull() {
            addCriterion("bank_code is null");
            return (Criteria) this;
        }

        public Criteria andBankCodeIsNotNull() {
            addCriterion("bank_code is not null");
            return (Criteria) this;
        }

        public Criteria andBankCodeEqualTo(String value) {
            addCriterion("bank_code =", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeNotEqualTo(String value) {
            addCriterion("bank_code <>", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeGreaterThan(String value) {
            addCriterion("bank_code >", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeGreaterThanOrEqualTo(String value) {
            addCriterion("bank_code >=", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeLessThan(String value) {
            addCriterion("bank_code <", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeLessThanOrEqualTo(String value) {
            addCriterion("bank_code <=", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeLike(String value) {
            addCriterion("bank_code like", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeNotLike(String value) {
            addCriterion("bank_code not like", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeIn(List<String> values) {
            addCriterion("bank_code in", values, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeNotIn(List<String> values) {
            addCriterion("bank_code not in", values, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeBetween(String value1, String value2) {
            addCriterion("bank_code between", value1, value2, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeNotBetween(String value1, String value2) {
            addCriterion("bank_code not between", value1, value2, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankLangCodeIsNull() {
            addCriterion("bank_lang_code is null");
            return (Criteria) this;
        }

        public Criteria andBankLangCodeIsNotNull() {
            addCriterion("bank_lang_code is not null");
            return (Criteria) this;
        }

        public Criteria andBankLangCodeEqualTo(String value) {
            addCriterion("bank_lang_code =", value, "bankLangCode");
            return (Criteria) this;
        }

        public Criteria andBankLangCodeNotEqualTo(String value) {
            addCriterion("bank_lang_code <>", value, "bankLangCode");
            return (Criteria) this;
        }

        public Criteria andBankLangCodeGreaterThan(String value) {
            addCriterion("bank_lang_code >", value, "bankLangCode");
            return (Criteria) this;
        }

        public Criteria andBankLangCodeGreaterThanOrEqualTo(String value) {
            addCriterion("bank_lang_code >=", value, "bankLangCode");
            return (Criteria) this;
        }

        public Criteria andBankLangCodeLessThan(String value) {
            addCriterion("bank_lang_code <", value, "bankLangCode");
            return (Criteria) this;
        }

        public Criteria andBankLangCodeLessThanOrEqualTo(String value) {
            addCriterion("bank_lang_code <=", value, "bankLangCode");
            return (Criteria) this;
        }

        public Criteria andBankLangCodeLike(String value) {
            addCriterion("bank_lang_code like", value, "bankLangCode");
            return (Criteria) this;
        }

        public Criteria andBankLangCodeNotLike(String value) {
            addCriterion("bank_lang_code not like", value, "bankLangCode");
            return (Criteria) this;
        }

        public Criteria andBankLangCodeIn(List<String> values) {
            addCriterion("bank_lang_code in", values, "bankLangCode");
            return (Criteria) this;
        }

        public Criteria andBankLangCodeNotIn(List<String> values) {
            addCriterion("bank_lang_code not in", values, "bankLangCode");
            return (Criteria) this;
        }

        public Criteria andBankLangCodeBetween(String value1, String value2) {
            addCriterion("bank_lang_code between", value1, value2, "bankLangCode");
            return (Criteria) this;
        }

        public Criteria andBankLangCodeNotBetween(String value1, String value2) {
            addCriterion("bank_lang_code not between", value1, value2, "bankLangCode");
            return (Criteria) this;
        }

        public Criteria andSingleLimitIsNull() {
            addCriterion("single_limit is null");
            return (Criteria) this;
        }

        public Criteria andSingleLimitIsNotNull() {
            addCriterion("single_limit is not null");
            return (Criteria) this;
        }

        public Criteria andSingleLimitEqualTo(String value) {
            addCriterion("single_limit =", value, "singleLimit");
            return (Criteria) this;
        }

        public Criteria andSingleLimitNotEqualTo(String value) {
            addCriterion("single_limit <>", value, "singleLimit");
            return (Criteria) this;
        }

        public Criteria andSingleLimitGreaterThan(String value) {
            addCriterion("single_limit >", value, "singleLimit");
            return (Criteria) this;
        }

        public Criteria andSingleLimitGreaterThanOrEqualTo(String value) {
            addCriterion("single_limit >=", value, "singleLimit");
            return (Criteria) this;
        }

        public Criteria andSingleLimitLessThan(String value) {
            addCriterion("single_limit <", value, "singleLimit");
            return (Criteria) this;
        }

        public Criteria andSingleLimitLessThanOrEqualTo(String value) {
            addCriterion("single_limit <=", value, "singleLimit");
            return (Criteria) this;
        }

        public Criteria andSingleLimitLike(String value) {
            addCriterion("single_limit like", value, "singleLimit");
            return (Criteria) this;
        }

        public Criteria andSingleLimitNotLike(String value) {
            addCriterion("single_limit not like", value, "singleLimit");
            return (Criteria) this;
        }

        public Criteria andSingleLimitIn(List<String> values) {
            addCriterion("single_limit in", values, "singleLimit");
            return (Criteria) this;
        }

        public Criteria andSingleLimitNotIn(List<String> values) {
            addCriterion("single_limit not in", values, "singleLimit");
            return (Criteria) this;
        }

        public Criteria andSingleLimitBetween(String value1, String value2) {
            addCriterion("single_limit between", value1, value2, "singleLimit");
            return (Criteria) this;
        }

        public Criteria andSingleLimitNotBetween(String value1, String value2) {
            addCriterion("single_limit not between", value1, value2, "singleLimit");
            return (Criteria) this;
        }

        public Criteria andDayLimitIsNull() {
            addCriterion("day_limit is null");
            return (Criteria) this;
        }

        public Criteria andDayLimitIsNotNull() {
            addCriterion("day_limit is not null");
            return (Criteria) this;
        }

        public Criteria andDayLimitEqualTo(String value) {
            addCriterion("day_limit =", value, "dayLimit");
            return (Criteria) this;
        }

        public Criteria andDayLimitNotEqualTo(String value) {
            addCriterion("day_limit <>", value, "dayLimit");
            return (Criteria) this;
        }

        public Criteria andDayLimitGreaterThan(String value) {
            addCriterion("day_limit >", value, "dayLimit");
            return (Criteria) this;
        }

        public Criteria andDayLimitGreaterThanOrEqualTo(String value) {
            addCriterion("day_limit >=", value, "dayLimit");
            return (Criteria) this;
        }

        public Criteria andDayLimitLessThan(String value) {
            addCriterion("day_limit <", value, "dayLimit");
            return (Criteria) this;
        }

        public Criteria andDayLimitLessThanOrEqualTo(String value) {
            addCriterion("day_limit <=", value, "dayLimit");
            return (Criteria) this;
        }

        public Criteria andDayLimitLike(String value) {
            addCriterion("day_limit like", value, "dayLimit");
            return (Criteria) this;
        }

        public Criteria andDayLimitNotLike(String value) {
            addCriterion("day_limit not like", value, "dayLimit");
            return (Criteria) this;
        }

        public Criteria andDayLimitIn(List<String> values) {
            addCriterion("day_limit in", values, "dayLimit");
            return (Criteria) this;
        }

        public Criteria andDayLimitNotIn(List<String> values) {
            addCriterion("day_limit not in", values, "dayLimit");
            return (Criteria) this;
        }

        public Criteria andDayLimitBetween(String value1, String value2) {
            addCriterion("day_limit between", value1, value2, "dayLimit");
            return (Criteria) this;
        }

        public Criteria andDayLimitNotBetween(String value1, String value2) {
            addCriterion("day_limit not between", value1, value2, "dayLimit");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createtime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createtime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createtime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createtime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createtime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createtime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createtime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createtime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createtime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createtime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createtime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createtime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNull() {
            addCriterion("updatetime is null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNotNull() {
            addCriterion("updatetime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeEqualTo(Date value) {
            addCriterion("updatetime =", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotEqualTo(Date value) {
            addCriterion("updatetime <>", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThan(Date value) {
            addCriterion("updatetime >", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updatetime >=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThan(Date value) {
            addCriterion("updatetime <", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThanOrEqualTo(Date value) {
            addCriterion("updatetime <=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIn(List<Date> values) {
            addCriterion("updatetime in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotIn(List<Date> values) {
            addCriterion("updatetime not in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeBetween(Date value1, Date value2) {
            addCriterion("updatetime between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotBetween(Date value1, Date value2) {
            addCriterion("updatetime not between", value1, value2, "updatetime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}