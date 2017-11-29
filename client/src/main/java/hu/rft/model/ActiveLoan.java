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
@Table(name = "kvt_active_loans")
@NamedQueries({
    @NamedQuery(name = "ActiveLoans.findAll", query = "SELECT a FROM ActiveLoans a")})
public class ActiveLoan implements Serializable {

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

    public ActiveLoan() {
    }

    public ActiveLoan(Integer loanId) {
        this.loanId = loanId;
    }

    public ActiveLoan(Integer loanId, String bookIsbn, int quantity, int loanerId) {
        this.loanId = loanId;
        this.bookIsbn = bookIsbn;
        this.quantity = quantity;
        this.loanerId = loanerId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (loanId != null ? loanId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActiveLoan)) {
            return false;
        }
        ActiveLoan other = (ActiveLoan) object;
        if ((this.loanId == null && other.loanId != null) || (this.loanId != null && !this.loanId.equals(other.loanId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hu.rft.server.entity.ActiveLoans[ loanId=" + loanId + " ]";
    }
    
}
