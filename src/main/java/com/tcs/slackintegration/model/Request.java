package com.tcs.slackintegration.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "requests")
public class Request {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int req_id;
		
		@Column(name = "user_id")
		private String user_id;
		
		@Column(name = "response_url")
		private String res_url;
		
		@Column(name = "message")
		private String msg;
		
		@Column(name = "timestamp")
		private String ts;
		
		public Request() {
			super();
		}

		public Request(int req_id, String user_id, String res_url, String msg, String ts) {
			super();
			this.req_id = req_id;
			this.user_id = user_id;
			this.res_url = res_url;
			this.msg = msg;
			this.ts = ts;
		}

		public int getReq_id() {
			return req_id;
		}

		public void setReq_id(int req_id) {
			this.req_id = req_id;
		}

		public String getUser_id() {
			return user_id;
		}

		public void setUser_id(String user_id) {
			this.user_id = user_id;
		}

		public String getRes_url() {
			return res_url;
		}

		public void setRes_url(String res_url) {
			this.res_url = res_url;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		public String getTs() {
			return ts;
		}

		public void setTs(String ts) {
			this.ts = ts;
		}

		@Override
		public String toString() {
			return "Request [req_id=" + req_id + ", user_id=" + user_id + ", res_url=" + res_url + ", msg=" + msg
					+ ", ts=" + ts + "]";
		}
		
}
