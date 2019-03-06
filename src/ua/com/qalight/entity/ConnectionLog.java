package ua.com.qalight.entity;

public class ConnectionLog {

	private long time;
	private int sessionId;
	private String ip;

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public int getSessionId() {
		return sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public ConnectionLog(long time, int sessionId, String ip) {
		this.time = time;
		this.sessionId = sessionId;
		this.ip = ip;
	}

	public ConnectionLog() {
	}
	
	@Override
	public String toString() {
		return time + " " + sessionId + " " + ip;
	}
}
