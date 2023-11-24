package Consulta;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Atendimento {
    private LocalDate data;
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatoBr = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataString = (data != null) ? formatoBr.format(data) : "Data não disponível";
        String retorno = "Data: " + dataString;
        retorno += "\nInformações: " + descricao;
        return retorno;
    }
}
