/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.rft.server.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
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
@Table(name = "kvt_loan_history")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LoanHistory.findAll", query = "SELECT l FROM LoanHistory l")
    , @NamedQuery(name = "LoanHistory.findByLoanId", query = "SELECT l FROM LoanHistory l WHERE l.loanId = :loanId")
    , @NamedQuery(name = "LoanHistory.findByBookIsbn", query = "SELECT l FROM LoanHistory l WHERE l.bookIsbn = :bookIsbn")
    , @NamedQuery(name = "LoanHistory.findByQuantity", query = "SELECT l FROM LoanHistory l WHERE l.quantity = :quantity")
    , @NamedQuery(name = "LoanHistory.findByLoanerId", query = "SELECT l FROM LoanHistory l WHERE l.loanerId = :loanerId")
    , @NamedQuery(name = "LoanHistory.findByStartDate", query = "SELECT l FROM LoanHistory l WHERE l.startDate = :startDate")
    , @NamedQuery(name = "LoanHistory.findByReturnDeadline", query = "SELECT l FROM LoanHistory l WHERE l.returnDeadline = :returnDeadline")
    , @NamedQuery(name = "LoanHistory.findByReturnedOn", query = "SELECT l FROM LoanHistory l WHERE l.returnedOn = :returnedOn")
    , @NamedQuery(name = "LoanHistory.findByReturnedLate", query = "SELECT l FROM LoanHistory l WHERE l.returnedLate = :returnedLate")})
public class LoanHistory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "LOAN_ID")
    private Integer loanId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Column(name = "BOOK_ISBN")
    private String bookIsbn;
    @Basic(optional = false)
    @NotNull
    @Column(name = "QUANTITY")
    private int quantity;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LOANER_ID")
    private int loanerId;
    @Column(name = "START_DATE")
    private LocalDate startDate;
    @Column(name = "RETURN_DEADLINE")
    private LocalDate returnDeadline;
    @Column(name = "RETURNED_ON")
    private LocalDate returnedOn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "RETURNED_LATE")
    private String returnedLate;

    public LoanHistory() {
    }

    public LoanHistory(Integer loanId) {
        this.loanId = loanId;
    }

    public LoanHistory(Integer loanId, String bookIsbn, int quantity, int loanerId, String returnedLate) {
        this.loanId = loanId;
        this.bookIsbn = bookIsbn;
        this.quantity = quantity;
        this.loanerId = loanerId;
        this.returnedLate = returnedLate;
    }

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getLoanerId() {
        return loanerId;
    }

    public void setLoanerId(int loanerId) {
        this.loanerId = loanerId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getReturnDeadline() {
        return returnDeadline;
    }

    public void setReturnDeadline(LocalDate returnDeadline) {
        this.returnDeadline = returnDeadline;
    }

    public LocalDate getReturnedOn() {
        return returnedOn;
    }

    public void setReturnedOn(LocalDate returnedOn) {
        this.returnedOn = returnedOn;
    }

    public String getReturnedLate() {
        return returnedLate;
    }

    public void setReturnedLate(String returnedLate) {
        this.returnedLate = returnedLate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (loanId != null ? loanId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LoanHistory)) {
            return false;
        }
        LoanHistory other = (LoanHistory) object;
        if ((this.loanId == null && other.loanId != null) || (this.loanId != null && !this.loanId.equals(other.loanId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hu.rft.server.entity.LoanHistory[ loanId=" + loanId + " ]";
    }
    
}
