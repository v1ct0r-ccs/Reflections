package ReflectionAnotation.Cadastro;

import ReflectionAnotation.Cadastro.Exception.TipoChaveNaoEncontradaException;
import ReflectionAnotation.Cadastro.dao.Cliente.ClienteMapDAO;
import ReflectionAnotation.Cadastro.dao.Cliente.IClienteDAO;
import ReflectionAnotation.Cadastro.dao.Produto.IProdutoDAO;
import ReflectionAnotation.Cadastro.dao.Produto.ProdutoDAO;
import ReflectionAnotation.Cadastro.domain.Cliente;
import ReflectionAnotation.Cadastro.domain.Produto;

import javax.swing.*;
import java.awt.*;

public class App extends Component {
    private static IClienteDAO iClienteDAO;
    private static IProdutoDAO iProdutoDAO;

    public static void main(String[] args) throws TipoChaveNaoEncontradaException {
        iClienteDAO = new ClienteMapDAO();
        iProdutoDAO = new ProdutoDAO();

        //***** Tela Inicial *****
        String opcaoInicial = JOptionPane.showInputDialog(null,
                "1 - Cliente, 2 - Produto ou 3 - Sair",
                "Escolha uma opção", JOptionPane.INFORMATION_MESSAGE);

        while (!isOpcaoValida(opcaoInicial)) {
            if ("".equals(opcaoInicial)) {
                opcaoInicial = JOptionPane.showInputDialog(null,
                        "Opção inválida! Digite 1 para cadastrar Cliente, 2 para cadastrar Produto ou 3 para sair",
                        "Escolha uma opção valida", JOptionPane.INFORMATION_MESSAGE);
            }

        }

        while (isOpcaoValida(opcaoInicial)) {
            if (isOpcaoSair(opcaoInicial)) {
                sair();
            } else if (isAcessoCadastroCliente(opcaoInicial)) {

                exibirTelaCadastroCliente();
            } else if (isAcessoCadastroProduto(opcaoInicial)) {
                exibirTelaCadastroProduto();
            } else if (opcaoInicial.equals("3")) {
                sair();
            }

            opcaoInicial = JOptionPane.showInputDialog(null,
                    "1 - Cliente, 2 - Produto ou 3 - Sair",
                    "Escolha uma opção", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static boolean isAcessoCadastroCliente(String opcao) {
        return opcao.equals("1");
    }

    private static boolean isAcessoCadastroProduto(String opcao) {
        return opcao.equals("2");
    }

    // **** Tela Cliente ****
    private static void exibirTelaCadastroCliente() throws TipoChaveNaoEncontradaException {
        String opcao = JOptionPane.showInputDialog(null,
                "Digite 1 para cadastro, 2 para consulta,3 para alteração, 4 para excluir, 5 para voltar ou 6 para sair",
                "Cadastro", JOptionPane.INFORMATION_MESSAGE);

        while (!isOpcaoValida(opcao)) {
            if ("".equals(opcao)) {
                sair();
            }
            opcao = JOptionPane.showInputDialog(null,
                    "Opção inválida! Digite 1 para cadastro, 2 para consulta, 3 para alteração, 4 para excluir, 5 para voltar ou 6 para sair",
                    "Escolha uma opção valida", JOptionPane.INFORMATION_MESSAGE);
        }

        while (isOpcaoValida(opcao)) {
            if (isOpcaoSair(opcao)) {
                sair();
            } else if (isCadastro(opcao)) {
                String dados = JOptionPane.showInputDialog(null,
                        "Digite os dados do cliente separados por vígula, conforme exemplo: Nome, CPF, Telefone, Endereço, Número, Cidade e Estado",
                        "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                cadastrarCliente(dados);

            } else if (isConsultar(opcao)) {
                String dados = JOptionPane.showInputDialog(null,
                        "Digite o cpf",
                        "Consultar", JOptionPane.INFORMATION_MESSAGE);
                consultar(dados);

            } else if (isAtualizarDados(opcao)) {
                String cpf = JOptionPane.showInputDialog(null,
                        "Digite o CPF do cliente que você quer alterar o cadatro",
                        "Alterar Cadastro", JOptionPane.INFORMATION_MESSAGE);
                Cliente cliente = iClienteDAO.consultar(Long.valueOf(cpf));
                if (cliente != null) {
                    String dados = JOptionPane.showInputDialog(null, "Digite os novos dados do cliente separado por vírgula: Nome, Telefone, Endereço, Número, Cidade e  Estado", "Atualizar Dados do Cliente", JOptionPane.INFORMATION_MESSAGE);
                    atualizarDados(dados, cliente);
                } else {
                    JOptionPane.showMessageDialog(null, "Cliente com o CPF " + cpf + " não encontrado, verifique os dados preenchidos.");
                }

            } else if (isExcluir(opcao)) {
                int escolha = JOptionPane.showConfirmDialog(null,
                        "Deseja realmente excluir o cadastro de um cliente?", "ATENÇÃO",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (escolha == JOptionPane.YES_OPTION) {
                    String cpf = JOptionPane.showInputDialog(null,
                            "Digite o CPF do cliente que você quer excluir o cadatro",
                            "Exluir Cadastro", JOptionPane.INFORMATION_MESSAGE);

                    if (isValidoCPF(cpf)) {
                        Cliente cliente = iClienteDAO.consultar(Long.valueOf(cpf));
                        if (cliente != null) {
                            excluir(cliente.getCpf());
                            JOptionPane.showMessageDialog(null, "Cadastro do cliente excluído com sucesso!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Cliente não encontrado");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "CPF inválido.");
                    }
                }

            } else if (opcao.equals("5")) {
                break;
            }

            opcao = JOptionPane.showInputDialog(null,
                    "Digite 1 para cadastro, 2 para consulta, 3 para alteração, 4 para excluir, 5 para voltar ou 6 para sair",
                    "Cadastro", JOptionPane.INFORMATION_MESSAGE);
        }
    }




    // ***** Metodos Cliente *****
    private static void cadastrarCliente(String dados) throws TipoChaveNaoEncontradaException {
        String[] dadosSeparados = dados.split(",");
        //Tentar validar se todos os campos estão preenchidos.
        //Se não tiver, passar null no construtor onde o valor é nulo
        Cliente cliente = new Cliente(dadosSeparados[0], dadosSeparados[1], dadosSeparados[2], dadosSeparados[3], dadosSeparados[4], dadosSeparados[5], dadosSeparados[6]);
        Boolean isCadastrado = iClienteDAO.cadastrar(cliente);
        if (isCadastrado) {
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso ", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Cliente já se encontra cadastrado", "Erro", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void consultar(String dados) {
        //Validar se foi passado somente o cpf
        Cliente cliente = iClienteDAO.consultar(Long.valueOf(dados));
        if (cliente != null) {
            JOptionPane.showMessageDialog(null, "Cliente encontrado: \n" + cliente.toString(),
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado: ",
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private static void atualizarDados(String dados, Cliente cliente) {
        String[] dadosSeparados = dados.split(",");
        if (dadosSeparados.length != 6) {
            JOptionPane.showInputDialog(null, "Dados inválidos, Cetifique-se de preencher todos os campos corretamente");
            return;
        }

        if (cliente != null) {
            cliente.setNome(dadosSeparados[0]);
            cliente.setTel(Long.valueOf(dadosSeparados[1]));
            cliente.setEnd(dadosSeparados[2]);
            cliente.setNumero(Integer.valueOf(dadosSeparados[3]));
            cliente.setCidade(dadosSeparados[4]);
            cliente.setEstado(dadosSeparados[5]);

            iClienteDAO.alterar(cliente);
            JOptionPane.showMessageDialog(null, "Dados do cliente atualizados com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Cliente com o CPF " + cliente + " não encontrado.");
        }
    }

    private static void excluir(String cpf) {
        iClienteDAO.excluir(cpf);
    }

    private static void sair() {
        JOptionPane.showMessageDialog(null, "Até logo: ", "Sair", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }


    // ***** Opção Cliente *****
    private static boolean isOpcaoValida(String opcao) {
        return "1".equals(opcao) || "2".equals(opcao)
                || "3".equals(opcao) || "4".equals(opcao) || "5".equals(opcao) || "6".equals(opcao);
    }

    private static boolean isValidoCPF(String cpf) {
        try {
            Long.parseLong(cpf);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isCadastro(String opcao) {
        return "1".equals(opcao);
    }

    private static boolean isConsultar(String opcao) {
        return "2".equals(opcao);
    }

    private static boolean isAtualizarDados(String opcao) {
        return "3".equals(opcao);
    }

    private static boolean isExcluir(String opcao) {
        return "4".equals(opcao);
    }

    private static boolean isOpcaoSair(String opcao) {
        return "6".equals(opcao);
    }


    //***** Tela Produto ****
    private static void exibirTelaCadastroProduto() {
        String opcao = JOptionPane.showInputDialog(null,
                "Digite 1 para cadastro, 2 para consulta, 3 para alteração, 4 para excluir, 5 para voltar ou 6 para sair",
                "Cadastro", JOptionPane.INFORMATION_MESSAGE);

        while (!isOpValidaProduto(opcao)) {
            if ("".equals(opcao)) {
                sair();
            }
            opcao = JOptionPane.showInputDialog(null,
                    "Opção inválida! Digite 1 para cadastro, 2 para consulta, 3 para alteração, 4 para excluir, 5 para voltar ou 6 para sair",
                    "Escolha uma opção valida", JOptionPane.INFORMATION_MESSAGE);
        }

        while (isOpValidaProduto(opcao)) {
            if (isOpcaoSair(opcao)) {
                sair();
            }else if (isCadastroPro(opcao)) {
                String dados = JOptionPane.showInputDialog(null, "Digite os dados do produto separado por vírgula: Código, Nome, Preço, Quantidade",
                        "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                cadastrarProduto(dados);

            } else if (isConsultarPro(opcao)) {
                String codigo = JOptionPane.showInputDialog(null, "Digite o Código do produto que deseja consultar",
                        "Consulta de Produtos", JOptionPane.INFORMATION_MESSAGE);
                consultarProduto(codigo);

            } else if (isAlterarPro(opcao)) {
                String codigo = JOptionPane.showInputDialog(null, "Digite o Código do Produto que deseja atualizar",
                        "Atualizar Produto", JOptionPane.INFORMATION_MESSAGE);
                if (isCodigoValido(codigo) && iProdutoDAO.consultar(Long.parseLong(codigo)) != null) {
                    String dados = JOptionPane.showInputDialog(null, "Digite os novos dados do produto separados por vírgula: Nome, Preço, Quantidade",
                            "Atualizar Produto", JOptionPane.INFORMATION_MESSAGE);
                    dados = codigo + "," + dados;
                    atualizarProduto(dados);
                } else {
                    JOptionPane.showMessageDialog(null, "Códiso inválido ou produdo não cadastrado");
                }

            } else if (isExcluirPro(opcao)) {
                String codigo = JOptionPane.showInputDialog(null, "Digite o código do produto que deseja excluir",
                        "Excluir Produto", JOptionPane.INFORMATION_MESSAGE);
                excluirProduto(Long.valueOf(codigo));

            }else if (opcao.equals("5")) {
                break;
            } else if (isSairPro(opcao)) {
                JOptionPane.showMessageDialog(null, "Até logo!");
                sair();
            } else {
                JOptionPane.showMessageDialog(null, "Opção Inválida");
            }
            opcao = JOptionPane.showInputDialog(null,
                    "Digite 1 para cadastro, 2 para consulta, 3 para alteração, 4 para excluir, 5 para voltar ou 6 para sair",
                    "Escolha uma opção", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // ***** Metodos Produto *****
    private static void cadastrarProduto(String dados) {
        String[] dadosSeparados = dados.split(",");

        try {
            long codigo = Long.parseLong(dadosSeparados[0]);
            String nome = dadosSeparados[1];
            double preco = Double.parseDouble(dadosSeparados[2]);
            Integer quant = Integer.parseInt(dadosSeparados[3]);

            Produto produto = new Produto();

            boolean cadastrado = iProdutoDAO.cadastrar(produto);
            if (cadastrado) {
                JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso",
                        "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Produto já cadastrado", "Erro", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Dados inválidods. Certifique-se de fornecer todos os campos corretamente");
        } catch (TipoChaveNaoEncontradaException e) {
            throw new RuntimeException(e);
        }
    }

    private static void consultarProduto(String codigo) {
        if (isCodigoValido(codigo)) {
            Produto produto = iProdutoDAO.consultar(Long.parseLong(codigo));
            if (produto != null) {
                JOptionPane.showMessageDialog(null, "Produto encontrado: \n" +
//                               "Código: " + produto.getCodigo() + "\n" +
                                "Nome: " + produto.getNome() + "\n" +
                                "Preço: " + produto.getPreco() + "\n" +
                                "Quantidade: " + produto.getQuant(),
                        "Consulsta de Produto", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Produto não encontrado",
                        "Erro", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Código inválido", "Erro", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void atualizarProduto(String dados) {
        String [] dadosSeparados = dados.split(",");

        try {
            Long codigo = Long.parseLong(dadosSeparados[0]);
            String nome = dadosSeparados[1];
            double preco = Double.parseDouble(dadosSeparados[2]);
            Integer quant = Integer.parseInt(dadosSeparados[3]);

            Produto produto = new Produto();

            iProdutoDAO.alterar(produto);

            JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso",
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Código inválido",
                    "Erro", JOptionPane.INFORMATION_MESSAGE);
        } catch (TipoChaveNaoEncontradaException e) {
            throw new RuntimeException(e);
        }
    }

    private static void excluirProduto(Long codigo) {
        if (isCodigoValido(String.valueOf(codigo))) {
            boolean excluido = iProdutoDAO.excluir(codigo);
            if (excluido) {
                JOptionPane.showMessageDialog(null, "Produto excluído com sucesso",
                        "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Produto não encontrado",
                        "Erro", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Código inválido",
                    "Erro", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    // ******* Opção Produto *******
    private static boolean isOpValidaProduto(String opcao) {
        return "1".equals(opcao) || "2".equals(opcao)
                || "3".equals(opcao) || "4".equals(opcao) || "5".equals(opcao) || "6".equals(opcao);
    }

    private static boolean isCodigoValido(String codigo) {
        try {
            Long.parseLong(codigo);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isCadastroPro(String opcao) {
        return "1".equals(opcao);
    }

    private static boolean isConsultarPro(String opcao) {
        return "2".equals(opcao);
    }

    private static boolean isAlterarPro(String opcao) {
        return "3".equals(opcao);
    }

    private static boolean isExcluirPro(String opcao) {
        return "4".equals(opcao);
    }

    private static boolean isSairPro(String opcao) {
        return "6".equals(opcao);
    }
}    