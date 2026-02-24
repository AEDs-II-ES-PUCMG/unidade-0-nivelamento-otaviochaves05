public class ProdutoNaoPerecivel extends Produto {
  public ProdutoNaoPerecivel(String descicao, double precoCusto, double margemLucro){
    super(descicao, precoCusto, margemLucro);
  }

  public ProdutoNaoPerecivel(String descicao, double precoCusto){
    super(descicao, precoCusto);
  }

  @Override
  public double valorDeVenda() {
    return precoCusto * (1 + margemLucro);
  }
}
