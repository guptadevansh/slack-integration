package com.tcs.slackintegration.utility;

import java.util.Arrays;

public class History {

	private Boolean ok;
	private Message[] messages;
	private Boolean has_more;
	private int pin_count;
	private Response_metadata response_metadata;
	
	public Boolean getOk() {
		return ok;
	}
	public void setOk(Boolean ok) {
		this.ok = ok;
	}
	public Message[] getMessages() {
		return messages;
	}
	public void setMessages(Message[] messages) {
		this.messages = messages;
	}
	public Boolean getHas_more() {
		return has_more;
	}
	public void setHas_more(Boolean has_more) {
		this.has_more = has_more;
	}
	public int getPin_count() {
		return pin_count;
	}
	public void setPin_count(int pin_count) {
		this.pin_count = pin_count;
	}
	public Response_metadata getResponse_metadata() {
		return response_metadata;
	}
	public void setResponse_metadata(Response_metadata response_metadata) {
		this.response_metadata = response_metadata;
	}
	@Override
	public String toString() {
		return "History [ok=" + ok + ", messages=" + Arrays.toString(messages) + ", has_more=" + has_more
				+ ", pin_count=" + pin_count + ", response_metadata=" + (response_metadata) + "]";
	}
	
}

class Response_metadata {
	
	private String next_cursor;

	public String getNext_cursor() {
		return next_cursor;
	}

	public void setNext_cursor(String next_cursor) {
		this.next_cursor = next_cursor;
	}

	@Override
	public String toString() {
		return "Response_metadata [next_cursor=" + next_cursor + "]";
	}
	
}