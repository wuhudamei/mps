
package cn.damei.entity.modules;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import cn.damei.common.persistence.DataEntity;
import cn.damei.common.persistence.DataEntity2;


public class ReciveJson{
	private static final long serialVersionUID = 1L;
	private String business_type;
	private String store_id;
	private String project_mode;
	private String order_number;
	private String engin_depart_id;
	private String contract_number;
	private String customer_type;
	private String customer_description;
	private String customer_name;
	private String customer_phone;
	private String customer_address;
	private String is_longway_commission;
	private String longway_amount;
	private String community_name;
	private String detail_address;
	private String build_number;
	private String build_unit;
	private String build_room;
	private String map_coordinate;
	private String province ;
	private String city;
	private String county ;
	private String sale_type;
	private String build_type;
	private String house_type;
	private String house_is_new;
	private String is_elevator;
	private String designer_name;
	private String designer_phone;
	private String order_reporter_name;
	private String order_reporter_phone;
	private String service_name;
	private String service_phone;
	private String contract_start_date;
	private String contract_end_date;
	private String covered_area;
	private String contract_area;
	private String contract_time;
	private String sign_contract_date;
	private String biz_order_accept_area;

	private String key;
	private String type;
	private String orderId;
	private String amount;
	private String time;
	private String customer_level;
	private String contract_amount;
	private String tagname;
	private String auditor_realname;
	private String auditor_mobile;
	private String paymentStatus;


	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getTagname() {
		return tagname;
	}
	public void setTagname(String tagname) {
		this.tagname = tagname;
	}
	public String getAuditor_realname() {
		return auditor_realname;
	}
	public void setAuditor_realname(String auditor_realname) {
		this.auditor_realname = auditor_realname;
	}
	public String getAuditor_mobile() {
		return auditor_mobile;
	}
	public void setAuditor_mobile(String auditor_mobile) {
		this.auditor_mobile = auditor_mobile;
	}
	public String getCustomer_level() {
		return customer_level;
	}
	public void setCustomer_level(String customer_level) {
		this.customer_level = customer_level;
	}
	public String getContract_amount() {
		return contract_amount;
	}
	public void setContract_amount(String contract_amount) {
		this.contract_amount = contract_amount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getBusiness_type() {
		return business_type;
	}
	public void setBusiness_type(String business_type) {
		this.business_type = business_type;
	}
	public String getStore_id() {
		return store_id;
	}
	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}
	public String getProject_mode() {
		return project_mode;
	}
	public void setProject_mode(String project_mode) {
		this.project_mode = project_mode;
	}
	public String getOrder_number() {
		return order_number;
	}
	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}
	public String getEngin_depart_id() {
		return engin_depart_id;
	}
	public void setEngin_depart_id(String engin_depart_id) {
		this.engin_depart_id = engin_depart_id;
	}
	public String getContract_number() {
		return contract_number;
	}
	public void setContract_number(String contract_number) {
		this.contract_number = contract_number;
	}
	public String getCustomer_type() {
		return customer_type;
	}
	public void setCustomer_type(String customer_type) {
		this.customer_type = customer_type;
	}
	public String getCustomer_description() {
		return customer_description;
	}
	public void setCustomer_description(String customer_description) {
		this.customer_description = customer_description;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getCustomer_phone() {
		return customer_phone;
	}
	public void setCustomer_phone(String customer_phone) {
		this.customer_phone = customer_phone;
	}
	public String getCustomer_address() {
		return customer_address;
	}
	public void setCustomer_address(String customer_address) {
		this.customer_address = customer_address;
	}
	public String getIs_longway_commission() {
		return is_longway_commission;
	}
	public void setIs_longway_commission(String is_longway_commission) {
		this.is_longway_commission = is_longway_commission;
	}
	public String getLongway_amount() {
		return longway_amount;
	}
	public void setLongway_amount(String longway_amount) {
		this.longway_amount = longway_amount;
	}
	public String getCommunity_name() {
		return community_name;
	}
	public void setCommunity_name(String community_name) {
		this.community_name = community_name;
	}
	public String getDetail_address() {
		return detail_address;
	}
	public void setDetail_address(String detail_address) {
		this.detail_address = detail_address;
	}
	public String getBuild_number() {
		return build_number;
	}
	public void setBuild_number(String build_number) {
		this.build_number = build_number;
	}
	public String getBuild_unit() {
		return build_unit;
	}
	public void setBuild_unit(String build_unit) {
		this.build_unit = build_unit;
	}
	public String getBuild_room() {
		return build_room;
	}
	public void setBuild_room(String build_room) {
		this.build_room = build_room;
	}
	public String getMap_coordinate() {
		return map_coordinate;
	}
	public void setMap_coordinate(String map_coordinate) {
		this.map_coordinate = map_coordinate;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getSale_type() {
		return sale_type;
	}
	public void setSale_type(String sale_type) {
		this.sale_type = sale_type;
	}
	public String getBuild_type() {
		return build_type;
	}
	public void setBuild_type(String build_type) {
		this.build_type = build_type;
	}
	public String getHouse_type() {
		return house_type;
	}
	public void setHouse_type(String house_type) {
		this.house_type = house_type;
	}
	public String getHouse_is_new() {
		return house_is_new;
	}
	public void setHouse_is_new(String house_is_new) {
		this.house_is_new = house_is_new;
	}
	public String getIs_elevator() {
		return is_elevator;
	}
	public void setIs_elevator(String is_elevator) {
		this.is_elevator = is_elevator;
	}
	public String getDesigner_name() {
		return designer_name;
	}
	public void setDesigner_name(String designer_name) {
		this.designer_name = designer_name;
	}
	public String getDesigner_phone() {
		return designer_phone;
	}
	public void setDesigner_phone(String designer_phone) {
		this.designer_phone = designer_phone;
	}
	public String getOrder_reporter_name() {
		return order_reporter_name;
	}
	public void setOrder_reporter_name(String order_reporter_name) {
		this.order_reporter_name = order_reporter_name;
	}
	public String getOrder_reporter_phone() {
		return order_reporter_phone;
	}
	public void setOrder_reporter_phone(String order_reporter_phone) {
		this.order_reporter_phone = order_reporter_phone;
	}
	public String getService_name() {
		return service_name;
	}
	public void setService_name(String service_name) {
		this.service_name = service_name;
	}
	public String getService_phone() {
		return service_phone;
	}
	public void setService_phone(String service_phone) {
		this.service_phone = service_phone;
	}
	public String getContract_start_date() {
		return contract_start_date;
	}
	public void setContract_start_date(String contract_start_date) {
		this.contract_start_date = contract_start_date;
	}
	public String getContract_end_date() {
		return contract_end_date;
	}
	public void setContract_end_date(String contract_end_date) {
		this.contract_end_date = contract_end_date;
	}
	public String getCovered_area() {
		return covered_area;
	}
	public void setCovered_area(String covered_area) {
		this.covered_area = covered_area;
	}
	public String getContract_area() {
		return contract_area;
	}
	public void setContract_area(String contract_area) {
		this.contract_area = contract_area;
	}
	public String getContract_time() {
		return contract_time;
	}
	public void setContract_time(String contract_time) {
		this.contract_time = contract_time;
	}
	public String getSign_contract_date() {
		return sign_contract_date;
	}
	public void setSign_contract_date(String sign_contract_date) {
		this.sign_contract_date = sign_contract_date;
	}
	public String getBiz_order_accept_area() {
		return biz_order_accept_area;
	}
	public void setBiz_order_accept_area(String biz_order_accept_area) {
		this.biz_order_accept_area = biz_order_accept_area;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public ReciveJson(String business_type, String store_id, String project_mode, String order_number,
			String engin_depart_id, String contract_number, String customer_type, String customer_description,
			String customer_name, String customer_phone, String customer_address, String is_longway_commission,
			String longway_amount, String community_name, String detail_address, String build_number, String build_unit,
			String build_room, String map_coordinate, String province, String city, String county, String sale_type,
			String build_type, String house_type, String house_is_new, String is_elevator, String designer_name,
			String designer_phone, String order_reporter_name, String order_reporter_phone, String service_name,
			String service_phone, String contract_start_date, String contract_end_date, String covered_area,
			String contract_area, String contract_time, String sign_contract_date, String biz_order_accept_area,
			String key) {
		super();
		this.business_type = business_type;
		this.store_id = store_id;
		this.project_mode = project_mode;
		this.order_number = order_number;
		this.engin_depart_id = engin_depart_id;
		this.contract_number = contract_number;
		this.customer_type = customer_type;
		this.customer_description = customer_description;
		this.customer_name = customer_name;
		this.customer_phone = customer_phone;
		this.customer_address = customer_address;
		this.is_longway_commission = is_longway_commission;
		this.longway_amount = longway_amount;
		this.community_name = community_name;
		this.detail_address = detail_address;
		this.build_number = build_number;
		this.build_unit = build_unit;
		this.build_room = build_room;
		this.map_coordinate = map_coordinate;
		this.province = province;
		this.city = city;
		this.county = county;
		this.sale_type = sale_type;
		this.build_type = build_type;
		this.house_type = house_type;
		this.house_is_new = house_is_new;
		this.is_elevator = is_elevator;
		this.designer_name = designer_name;
		this.designer_phone = designer_phone;
		this.order_reporter_name = order_reporter_name;
		this.order_reporter_phone = order_reporter_phone;
		this.service_name = service_name;
		this.service_phone = service_phone;
		this.contract_start_date = contract_start_date;
		this.contract_end_date = contract_end_date;
		this.covered_area = covered_area;
		this.contract_area = contract_area;
		this.contract_time = contract_time;
		this.sign_contract_date = sign_contract_date;
		this.biz_order_accept_area = biz_order_accept_area;
		this.key = key;
	}
	public ReciveJson() {
		super();

	}
	
	
	
	
	
	
}