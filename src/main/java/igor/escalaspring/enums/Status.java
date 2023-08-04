package igor.escalaspring.enums;

public enum Status {
    ATIVO("Ativo"), INATIVO("Inativo");

    private String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    //toString
    @Override
    public String toString() {
        return this.value;
    }



}
