package juniorbraga.com.br.spents.enums;

public enum FormOfPaymentEnum {

    DINHEIRO(0,"Dinheiro"),
    CARTAO_DEBITO(1,"Cartão de Debito"),
    CARTAO_CREDITO(2,"Cartão de Crédito"),
    AUXILIO_ALIMENTACAO(3,"Alimentação"),
    AUXILIO_REFEICAO(4,"Refeição");

    private final int formOfPayment;
    private final String descriptionStr;

    FormOfPaymentEnum(int formOfPayment,String description){
        this.formOfPayment = formOfPayment;
        this.descriptionStr = description;
    }

    public String getDescriptionStr(){
        return descriptionStr;
    }

    public int getFormOfPayment(){
        return formOfPayment;
    }

    public static FormOfPaymentEnum getFormOfPayment(int payment){

        if(payment == DINHEIRO.getFormOfPayment()) {
            return DINHEIRO;
        }else if(payment == CARTAO_DEBITO.getFormOfPayment()) {
            return CARTAO_DEBITO;
        }else if(payment == CARTAO_CREDITO.getFormOfPayment()) {
            return CARTAO_CREDITO;
        }else if(payment == AUXILIO_ALIMENTACAO.getFormOfPayment()) {
            return AUXILIO_ALIMENTACAO;
        }else if(payment == AUXILIO_REFEICAO.getFormOfPayment()) {
            return AUXILIO_REFEICAO;
        }

        return DINHEIRO;
    }
}
