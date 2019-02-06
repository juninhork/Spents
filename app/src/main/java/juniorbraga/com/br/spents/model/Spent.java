package juniorbraga.com.br.spents.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "spent")
public class Spent {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    private int id;

    @ColumnInfo
    private String description;

    @ColumnInfo
    private int typeSpend;

    @ColumnInfo
    private int formOfPayment;

    @ColumnInfo
    private String payday;

    @ColumnInfo
    private boolean isPaidOut;

    @ColumnInfo
    private boolean isScheduled;

    @ColumnInfo
    private Double valuer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTypeSpend() {
        return typeSpend;
    }

    public void setTypeSpend(int typeSpend) {
        this.typeSpend = typeSpend;
    }

    public int getFormOfPayment() {
        return formOfPayment;
    }

    public void setFormOfPayment(int formOfPayment) {
        this.formOfPayment = formOfPayment;
    }

    public String getPayday() {
        return payday;
    }

    public void setPayday(String payday) {
        this.payday = payday;
    }

    public boolean isPaidOut() {
        return isPaidOut;
    }

    public void setPaidOut(boolean paidOut) {
        isPaidOut = paidOut;
    }

    public boolean isScheduled() {
        return isScheduled;
    }

    public void setScheduled(boolean scheduled) {
        isScheduled = scheduled;
    }

    public Double getValuer() {
        return valuer;
    }

    public void setValuer(Double valuer) {
        this.valuer = valuer;
    }

}