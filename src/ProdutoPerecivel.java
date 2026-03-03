import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ProdutoPerecivel extends Produto {
  private final static double DESCONTO = 0.25;
  private final static int PRAZO_DESCONTO = 7;
  private LocalDate dataDeValidade;
  
  public ProdutoPerecivel(String descricao, double precoCusto, double margemLucro, LocalDate validade) {
    super(descricao, precoCusto, margemLucro);
    if (validade.isBefore(LocalDate.now())) {
      throw new IllegalArgumentException("Não é possivel cadastrar produto vencido.");
    } else {
      this.dataDeValidade = validade;
    }
  }

  @Override
  public double valorDeVenda() {
    double desconto = 0d;
    int diasValidade = LocalDate.now().until(dataDeValidade).getDays();
    if (diasValidade <= PRAZO_DESCONTO){
      desconto = DESCONTO;
    }
    return (precoCusto * (1 + margemLucro))*(1 - desconto);
  }

  /**
  * Gera uma linha de texto a partir dos dados do produto. Preço e margem de lucro vão formatados com 2 casas
  decimais.
  * Data de validade vai no formato dd/mm/aaaa
  * @return Uma string no formato "2; descrição;preçoDeCusto;margemDeLucro;dataDeValidade"
  */
  @Override
  public String gerarDadosTexto() {
    DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String precoFormatado = String.format("%.2f", precoCusto).replace(",", ".");
    String margemFormatada = String.format("%.2f", margemLucro).replace(",", ".");
    String dataFormatada = formatoData.format(dataDeValidade);
    return String.format("2;%s;%s;%s;%s", descricao, precoFormatado, margemFormatada, dataFormatada);
  }

  @Override
  public String toString() {
    return "ProdutoPerecivel [precoCusto=" + precoCusto + ", dataDeValidade=" + dataDeValidade + ", margemLucro="
        + margemLucro + ", valorVenda()=" + valorDeVenda() + ", valorDeVenda()=" + valorDeVenda() +"]";
  }
}