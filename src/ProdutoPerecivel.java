import java.time.LocalDate;

public class ProdutoPerecivel extends Produto {
  private final static double DESCONTO = 0.25;
  private final static int PRAZO_DESCONTO = 7;
  private LocalDate dataDeValidade;
  
  public ProdutoPerecivel(String desc, double precoCusto, double margemLucro, LocalDate validade) {
    super(desc, precoCusto, margemLucro);
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

  @Override
  public String toString() {
    return "ProdutoPerecivel [precoCusto=" + precoCusto + ", dataDeValidade=" + dataDeValidade + ", margemLucro="
        + margemLucro + ", valorVenda()=" + valorDeVenda() + ", valorDeVenda()=" + valorDeVenda() +"]";
  }
}