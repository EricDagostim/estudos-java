package br.com.empresa.vo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "forcon")
public class ForconVO implements Serializable {

    private static final long serialVersionUID = -1766360062770004639L;

    @Id
    @NotNull
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "sq_forcon", sequenceName = "sq_forcon", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "sq_forcon")
    private BigInteger id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "contat", referencedColumnName = "id", nullable = false)
    private ContatVO contat;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "tipcon", nullable = false, length = 1)
    private String tipcon;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "dddcon", nullable = false, length = 2)
    private String dddcon;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "numcon", nullable = false, length = 10)
    private String numcon;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 400)
    @Column(name = "emacon", nullable = false, length = 400)
    private String emacon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "forcon", referencedColumnName = "id")
    private ForconVO forconVO;

    public ForconVO(BigInteger id) {
        this.id = id;
    }
	public ForconVO() {
	}

	public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public ContatVO getContat() {
        return contat;
    }

    public void setContat(ContatVO contat) {
        this.contat = contat;
    }

    public String getTipcon() {
        return tipcon;
    }

    public void setTipcon(String tipcon) {
        this.tipcon = tipcon;
    }

    public String getDddcon() {
        return dddcon;
    }

    public void setDddcon(String dddcon) {
        this.dddcon = dddcon;
    }

    public String getNumcon() {
        return numcon;
    }

    public void setNumcon(String numcon) {
        this.numcon = numcon;
    }

    public String getEmacon() {
        return emacon;
    }

    public void setEmacon(String emacon) {
        this.emacon = emacon;
    }

    public ForconVO getForconVO() {
        return forconVO;
    }

    public void setForconVO(ForconVO forconVO) {
        this.forconVO = forconVO;
    }

    @Override
    public String toString() {
        return "ContatVO [Id=" + id + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, forconVO);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ForconVO other = (ForconVO) obj;
        return Objects.equals(id, other.id) && Objects.equals(forconVO, other.forconVO);
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
