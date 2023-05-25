package com.example.application.entities;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

/**
 * @author tb 24.05.23
 * @since 24.05.23
 * Entität zum speichern der Keywords
 */

@Entity
@Table(name = "keyword", schema = "talent", catalog = "wi_projekt_mahbobi_sose2023")
public class Keyword {
    @Id
    @SequenceGenerator(name = "keyword_keywordid_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "keywordid")
    private int keywordid;
    @Basic
    @Column(name = "keywordname")
    private String keywordname;

    @ManyToMany(mappedBy = "zugehoerigerKey")
    Set<Stellenanzeige> zugehoerigerJob;

    public int getKeywordid() {
        return keywordid;
    }

    public void setKeywordid(int keywordid) {
        this.keywordid = keywordid;
    }

    public String getKeywordname() {
        return keywordname;
    }

    public void setKeywordname(String keywordname) {
        this.keywordname = keywordname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Keyword keyword = (Keyword) o;
        return keywordid == keyword.keywordid && Objects.equals(keywordname, keyword.keywordname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keywordid, keywordname);
    }

}
