package com.clientui.beans;

public class ExpeditionBean {


    private int idCommande;
    private int etat;
    private String etatLibelle;

    public ExpeditionBean() {}


    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getEtatLibelle() {
        return etatLibelle;
    }

    public void setEtatLibelle(String etatLibelle) {
        this.etatLibelle = etatLibelle;
    }

    @Override
    public String toString() {
        return "ExpeditionBean{" +
                "idCommande=" + idCommande +
                ", etat=" + etat +
                ", etatLibelle='" + etatLibelle + '\'' +
                '}';
    }
}
