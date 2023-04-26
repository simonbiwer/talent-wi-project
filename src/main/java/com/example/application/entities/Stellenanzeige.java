package org.hbrs.se2.project.gibralda.entities;

import com.example.application.entities.User;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "stellenanzeige", schema = "wi_projekt_mahbobi_sose2023", catalog = "wi_projekt_mahbobi_sose2023")
public class Stellenanzeige {
    @Id
    @SequenceGenerator(name = "stellenanzeige_stellenanzeigeid_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_job")
    private int id_job;
    @Basic
    @Column(name = "titel")
    private String titel;
    @Basic
    @Column(name = "beschreibung")
    private String beschreibung;
    @Basic
    @Column(name = "url")
    private String url;
    @Basic
    @Column(name = "technologien")
    private String technologien;
    @Basic
    @Column(name = "unternehmen")
    private String unternehmen;
    @Basic
    @Column(name = "projektdauer")
    private String projektdauer;
    @Basic
    @Column(name = "startdatum")
    private String startdatum;
    @Basic
    @Column(name = "qualifikation")
    private String qualifikation;



    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "userid", nullable = false)
    private User userid;
    @OneToMany(mappedBy = "jobByJobid")
    private Collection<Stellenanzeige> bewerbungsByJobid;

    public int getJobid() {
        return id_job;
    }

    public void setJobid(int jobid) {
        this.id_job = jobid;
    }

    public String getTitel() {
        return titel;
    }

    public void setJobname(String name) {
        this.titel = name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setJobart(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String technologien() {
        return technologien;
    }

    public void setTechnologien(String technologien) {
        this.technologien = technologien;
    }

    public String getUnternehmen() {
        return unternehmen;
    }

    public void setWasWirErwarten(String unternehmen) {
        this.unternehmen = unternehmen;
    }

    public String getProjektdauer() {
        return projektdauer;
    }

    public void setProjektdauer(String projektdauer) {
        this.projektdauer = projektdauer;
    }

    public String getStartdatum() {
        return startdatum;
    }

    public void setStartdatum(String startdatum) {
        this.startdatum = startdatum;
    }

    public String getQualifikation() {
        return qualifikation;
    }

    public void setUnternehmerByUserid(String qualifikation) {
        this.qualifikation = qualifikation;
    }




    @Override
    public boolean equals(Object o) {   //prüft ob Entität schon vorhanden
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stellenanzeige job = (Stellenanzeige) o;
        return id_job == job.id_job && Objects.equals(job.titel, titel) == false &&
                Objects.equals(beschreibung, job.beschreibung) &&
                Objects.equals(url, job.url) &&
                Objects.equals(technologien, job.technologien) &&
                Objects.equals(unternehmen, job.unternehmen) &&
                Objects.equals(projektdauer, job.projektdauer) &&
                Objects.equals(startdatum, job.startdatum) &&
                Objects.equals(qualifikation, job.qualifikation) &&
                Objects.equals(id_job, job.id_job);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_job, titel, beschreibung, url, technologien, unternehmen, projektdauer, startdatum, qualifikation);
    }
}
