/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.rft.server.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Dani
 */
@Entity
@Table(name = "kvt_event_history")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EventHistory.findAll", query = "SELECT e FROM EventHistory e")
    , @NamedQuery(name = "EventHistory.findByEventId", query = "SELECT e FROM EventHistory e WHERE e.eventId = :eventId")
    , @NamedQuery(name = "EventHistory.findByEventName", query = "SELECT e FROM EventHistory e WHERE e.eventName = :eventName")
    , @NamedQuery(name = "EventHistory.findByOrganizerId", query = "SELECT e FROM EventHistory e WHERE e.organizerId = :organizerId")
    , @NamedQuery(name = "EventHistory.findByEventLocation", query = "SELECT e FROM EventHistory e WHERE e.eventLocation = :eventLocation")
    , @NamedQuery(name = "EventHistory.findByEventBegan", query = "SELECT e FROM EventHistory e WHERE e.eventBegan = :eventBegan")
    , @NamedQuery(name = "EventHistory.findByEventEnded", query = "SELECT e FROM EventHistory e WHERE e.eventEnded = :eventEnded")
    , @NamedQuery(name = "EventHistory.findByExpectedPpl", query = "SELECT e FROM EventHistory e WHERE e.expectedPpl = :expectedPpl")
    , @NamedQuery(name = "EventHistory.findByAttendedPpl", query = "SELECT e FROM EventHistory e WHERE e.attendedPpl = :attendedPpl")})
public class EventHistory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "EVENT_ID")
    private Integer eventId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "EVENT_NAME")
    private String eventName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ORGANIZER_ID")
    private int organizerId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "EVENT_LOCATION")
    private String eventLocation;
    @Column(name = "EVENT_BEGAN")
    private LocalDateTime eventBegan;
    @Column(name = "EVENT_ENDED")
    private LocalDateTime eventEnded;
    @Column(name = "EXPECTED_PPL")
    private Integer expectedPpl;
    @Column(name = "ATTENDED_PPL")
    private Integer attendedPpl;

    public EventHistory() {
    }

    public EventHistory(Integer eventId) {
        this.eventId = eventId;
    }

    public EventHistory(Integer eventId, String eventName, int organizerId, String eventLocation) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.organizerId = organizerId;
        this.eventLocation = eventLocation;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(int organizerId) {
        this.organizerId = organizerId;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public LocalDateTime getEventBegan() {
        return eventBegan;
    }

    public void setEventBegan(LocalDateTime eventBegan) {
        this.eventBegan = eventBegan;
    }

    public LocalDateTime getEventEnded() {
        return eventEnded;
    }

    public void setEventEnded(LocalDateTime eventEnded) {
        this.eventEnded = eventEnded;
    }

    public Integer getExpectedPpl() {
        return expectedPpl;
    }

    public void setExpectedPpl(Integer expectedPpl) {
        this.expectedPpl = expectedPpl;
    }

    public Integer getAttendedPpl() {
        return attendedPpl;
    }

    public void setAttendedPpl(Integer attendedPpl) {
        this.attendedPpl = attendedPpl;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (eventId != null ? eventId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EventHistory)) {
            return false;
        }
        EventHistory other = (EventHistory) object;
        if ((this.eventId == null && other.eventId != null) || (this.eventId != null && !this.eventId.equals(other.eventId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hu.rft.server.entity.EventHistory[ eventId=" + eventId + " ]";
    }
    
}
