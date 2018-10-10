package com.clientui.enumeration;

public enum EtatEnum {

    PREPARATION(0, "En préparation"),
    EXPEDIEE(1, "Expédiée"),
    LIVREE(2, "Livrée");

    private int id;
    private String libelle;

    EtatEnum(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public int getId() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }

    public static EtatEnum getById(int id){
        for (EtatEnum etatEnum: EtatEnum.values()) {
            if(etatEnum.getId() == id){
                return etatEnum;
            }
        }
        return null;
    }
}
