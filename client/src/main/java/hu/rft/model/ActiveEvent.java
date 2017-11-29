/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.rft.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Dani
 */
@Entity
@Table(name = "kvt_active_events")
@NamedQueries({
    @NamedQuery(name = "ActiveEvents.findAll", query = "SELECT a FROM ActiveEvents a")})
public class ActiveEvent implements Serializable {

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
    @Column(name = "EVENT_BEGINS")
    private LocalDateTime eventBegins;
    @Column(name = "EVENT_ENDS")
    private LocalDateTime eventEnds;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MAX_PPL")
    private int maxPpl;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EXPECTED_PPL")
    private int expectedPpl;

    public ActiveEvent() {
    }

    public ActiveEvent(Integer eventId) {
        this.eventId = eventId;
    }

    public ActiveEvent(Integer eventId, String eventName, int organizerId, String eventLocation, int maxPpl, int expectedPpl) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.organizerId = organizerId;
        this.eventLocation = eventLocation;
        this.maxPpl = maxPpl;
        this.expectedPpl = expectedPpl;
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

    public LocalDateTime getEventBegins() {
        return eventBegins;
    }

    public void setEventBegins(LocalDateTime eventBegins) {
        this.eventBegins = eventBegins;
    }

    public LocalDateTime getEventEnds() {
        return eventEnds;
    }

    public void setEventEnds(LocalDateTime eventEnds) {
        this.eventEnds = eventEnds;
    }

    public int getMaxPpl() {
        return maxPpl;
    }

    public void setMaxPpl(int maxPpl) {
        this.maxPpl = maxPpl;
    }

    public int getExpectedPpl() {
        return expectedPpl;
    }

    public void setExpectedPpl(int expectedPpl) {
        this.expectedPpl = expectedPpl;
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
        if (!(object instanceof ActiveEvent)) {
            return false;
        }
        ActiveEvent other = (ActiveEvent) object;
        if ((this.eventId == null && other.eventId != null) || (this.eventId != null && !this.eventId.equals(other.eventId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hu.rft.server.entity.ActiveEvents[ eventId=" + eventId + " ]";
    }
    
}
