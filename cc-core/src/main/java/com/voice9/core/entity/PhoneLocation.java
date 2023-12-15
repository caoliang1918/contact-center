package com.voice9.core.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 *
 * @author caoliang
 * @date   2020/06/06
 */
public class PhoneLocation implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String ruid;

    /**
     * 
     */
    private String username;

    /**
     * 
     */
    private String domain;

    /**
     * 
     */
    private String contact;

    /**
     * 
     */
    private String received;

    /**
     * 
     */
    private String path;

    /**
     * 
     */
    private Date expires;

    /**
     * 
     */
    private Float q;

    /**
     * 
     */
    private String callid;

    /**
     * 
     */
    private Integer cseq;

    /**
     * 
     */
    private Date lastModified;

    /**
     * 
     */
    private Integer flags;

    /**
     * 
     */
    private Integer cflags;

    /**
     * 
     */
    private String userAgent;

    /**
     * 
     */
    private String socket;

    /**
     * 
     */
    private Integer methods;

    /**
     * 
     */
    private String instance;

    /**
     * 
     */
    private Integer regId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRuid() {
        return ruid;
    }

    public void setRuid(String ruid) {
        this.ruid = ruid == null ? null : ruid.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain == null ? null : domain.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getReceived() {
        return received;
    }

    public void setReceived(String received) {
        this.received = received == null ? null : received.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public Date getExpires() {
        return expires;
    }

    public void setExpires(Date expires) {
        this.expires = expires;
    }

    public Float getQ() {
        return q;
    }

    public void setQ(Float q) {
        this.q = q;
    }

    public String getCallid() {
        return callid;
    }

    public void setCallid(String callid) {
        this.callid = callid == null ? null : callid.trim();
    }

    public Integer getCseq() {
        return cseq;
    }

    public void setCseq(Integer cseq) {
        this.cseq = cseq;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public Integer getFlags() {
        return flags;
    }

    public void setFlags(Integer flags) {
        this.flags = flags;
    }

    public Integer getCflags() {
        return cflags;
    }

    public void setCflags(Integer cflags) {
        this.cflags = cflags;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent == null ? null : userAgent.trim();
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket == null ? null : socket.trim();
    }

    public Integer getMethods() {
        return methods;
    }

    public void setMethods(Integer methods) {
        this.methods = methods;
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance == null ? null : instance.trim();
    }

    public Integer getRegId() {
        return regId;
    }

    public void setRegId(Integer regId) {
        this.regId = regId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", ruid=").append(ruid);
        sb.append(", username=").append(username);
        sb.append(", domain=").append(domain);
        sb.append(", contact=").append(contact);
        sb.append(", received=").append(received);
        sb.append(", path=").append(path);
        sb.append(", expires=").append(expires);
        sb.append(", q=").append(q);
        sb.append(", callid=").append(callid);
        sb.append(", cseq=").append(cseq);
        sb.append(", lastModified=").append(lastModified);
        sb.append(", flags=").append(flags);
        sb.append(", cflags=").append(cflags);
        sb.append(", userAgent=").append(userAgent);
        sb.append(", socket=").append(socket);
        sb.append(", methods=").append(methods);
        sb.append(", instance=").append(instance);
        sb.append(", regId=").append(regId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        PhoneLocation other = (PhoneLocation) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getRuid() == null ? other.getRuid() == null : this.getRuid().equals(other.getRuid()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getDomain() == null ? other.getDomain() == null : this.getDomain().equals(other.getDomain()))
            && (this.getContact() == null ? other.getContact() == null : this.getContact().equals(other.getContact()))
            && (this.getReceived() == null ? other.getReceived() == null : this.getReceived().equals(other.getReceived()))
            && (this.getPath() == null ? other.getPath() == null : this.getPath().equals(other.getPath()))
            && (this.getExpires() == null ? other.getExpires() == null : this.getExpires().equals(other.getExpires()))
            && (this.getQ() == null ? other.getQ() == null : this.getQ().equals(other.getQ()))
            && (this.getCallid() == null ? other.getCallid() == null : this.getCallid().equals(other.getCallid()))
            && (this.getCseq() == null ? other.getCseq() == null : this.getCseq().equals(other.getCseq()))
            && (this.getLastModified() == null ? other.getLastModified() == null : this.getLastModified().equals(other.getLastModified()))
            && (this.getFlags() == null ? other.getFlags() == null : this.getFlags().equals(other.getFlags()))
            && (this.getCflags() == null ? other.getCflags() == null : this.getCflags().equals(other.getCflags()))
            && (this.getUserAgent() == null ? other.getUserAgent() == null : this.getUserAgent().equals(other.getUserAgent()))
            && (this.getSocket() == null ? other.getSocket() == null : this.getSocket().equals(other.getSocket()))
            && (this.getMethods() == null ? other.getMethods() == null : this.getMethods().equals(other.getMethods()))
            && (this.getInstance() == null ? other.getInstance() == null : this.getInstance().equals(other.getInstance()))
            && (this.getRegId() == null ? other.getRegId() == null : this.getRegId().equals(other.getRegId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRuid() == null) ? 0 : getRuid().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getDomain() == null) ? 0 : getDomain().hashCode());
        result = prime * result + ((getContact() == null) ? 0 : getContact().hashCode());
        result = prime * result + ((getReceived() == null) ? 0 : getReceived().hashCode());
        result = prime * result + ((getPath() == null) ? 0 : getPath().hashCode());
        result = prime * result + ((getExpires() == null) ? 0 : getExpires().hashCode());
        result = prime * result + ((getQ() == null) ? 0 : getQ().hashCode());
        result = prime * result + ((getCallid() == null) ? 0 : getCallid().hashCode());
        result = prime * result + ((getCseq() == null) ? 0 : getCseq().hashCode());
        result = prime * result + ((getLastModified() == null) ? 0 : getLastModified().hashCode());
        result = prime * result + ((getFlags() == null) ? 0 : getFlags().hashCode());
        result = prime * result + ((getCflags() == null) ? 0 : getCflags().hashCode());
        result = prime * result + ((getUserAgent() == null) ? 0 : getUserAgent().hashCode());
        result = prime * result + ((getSocket() == null) ? 0 : getSocket().hashCode());
        result = prime * result + ((getMethods() == null) ? 0 : getMethods().hashCode());
        result = prime * result + ((getInstance() == null) ? 0 : getInstance().hashCode());
        result = prime * result + ((getRegId() == null) ? 0 : getRegId().hashCode());
        return result;
    }
}