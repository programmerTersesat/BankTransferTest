package com.example.nando.demoajanando.model;



import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Data
@Builder
@Table(name = "tbl_account_bank")
public class AccountBank implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Nama Account Bank tidak boleh kosong")
    @Column(name = "name",length = 100)
    private String name;

    @NotEmpty(message = "Tanggal Lahir tidak boleh kosong")
    @Column(name = "datebirth",length = 100)
    private String datebirth;

    @NotEmpty(message = "Alamat tidak boleh kosong")
    @Column(name = "address",length = 100)
    private String address;

    private Double saldo;

    @Column(name = "created_at",length = 100)
    private String createdAt;

    @Column(name = "updated_at",length = 100)
    private String updatedAt;

    public AccountBank() {
    }

    public AccountBank(Long id, String name, String datebirth, String address, Double saldo, String createdAt, String updatedAt) {
        this.id = id;
        this.name = name;
        this.datebirth = datebirth;
        this.address = address;
        this.saldo = saldo;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDatebirth() {
        return datebirth;
    }

    public void setDatebirth(String datebirth) {
        this.datebirth = datebirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
