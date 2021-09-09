
package apresentacao;

import dados.Cliente;
import dados.Endereco;
import dados.Hotel;
import dados.Quarto;
import dados.Reserva;
import dados.Estadia;
import dados.Extra;
import dados.Servico;
import dados.TipoQuarto;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Scanner;
import persistencia.ClienteDAO;
import persistencia.EstadiaDAO;
import persistencia.ExtraDAO;
import persistencia.HotelDAO;
import persistencia.QuartoDAO;
import persistencia.ReservaDAO;
import persistencia.ServicoDAO;

public class Principal {
    public static void main(String[] args) throws UnknownHostException{
        ClienteDAO cliDAO = ClienteDAO.getInstance();
        HotelDAO hotDAO = HotelDAO.getInstance();
        QuartoDAO quaDAO = QuartoDAO.getInstance();
        ReservaDAO resDAO = ReservaDAO.getInstance();
        EstadiaDAO estDAO = EstadiaDAO.getInstance();
        ServicoDAO serDAO = ServicoDAO.getInstance();
        ExtraDAO extDAO = ExtraDAO.getInstance();
        Scanner scanner = new Scanner(System.in);
        //cliDAO.selectAll();
        Cliente c = new Cliente();
        Hotel h = new Hotel();
        Quarto q = new Quarto();
        Estadia es = new Estadia();
        Reserva r = new Reserva();
        Extra ex = new Extra();
        Servico s = new Servico();
        Endereco en = new Endereco();
        TipoQuarto t = new TipoQuarto();
        int codigo, rg, cont;
        int opcao = menu();
        
        while(opcao!=0){
            switch(opcao){
                case 1:
                    System.out.println("Digite o rg do Cliente que deseja inserir: ");
                    rg = scanner.nextInt();
                    clearBuffer(scanner);
                    c = new Cliente();
                    c = cliDAO.select(rg);
                    if (c != null) {
                        System.out.println("Cliente ja existente, nao eh possivel inserir!");
                    } else {
                        c = new Cliente();
                        c.setRg(rg);
                        System.out.println("Digite o nome do Cliente: ");
                        c.setNome(scanner.nextLine());
                        System.out.println("Digite o telefone do Cliente: ");
                        c.setTelefone(scanner.nextInt());
                        clearBuffer(scanner);
                        en = new Endereco();
                        System.out.println("Digite a rua do Cliente: ");
                        en.setRua(scanner.nextLine());
                        System.out.println("Digite a bairro do Cliente: ");
                        en.setBairro(scanner.nextLine());
                        System.out.println("Digite a cidade do Cliente: ");
                        en.setCidade(scanner.nextLine());
                        System.out.println("Digite o estaado do Cliente: ");
                        en.setEstado(scanner.nextLine());
                        c.setEndereco(en);
                        cliDAO.insert(c);
                        System.out.println("Cliente inserido!");
                    }
                break;
                case 2:
                    System.out.println("Digite o codigio do Hotel que deseja inserir: ");
                    codigo = scanner.nextInt();
                    clearBuffer(scanner);
                    h = hotDAO.select(codigo);
                    if (h != null) {
                        System.out.println("Hotel ja existente, nao eh possivel inserir!");
                    } else {
                        h = new Hotel();
                        h.setCodigo(codigo);
                        System.out.println("Digite o nome do Hotel: ");
                        h.setNome(scanner.nextLine());
                        System.out.println("Digite o telefone do Hotel: ");
                        h.setTelefone(scanner.nextInt());
                        clearBuffer(scanner);
                        en = new Endereco();
                        System.out.println("Digite a rua do Hotel: ");
                        en.setRua(scanner.nextLine());
                        System.out.println("Digite a bairro do Hotel: ");
                        en.setBairro(scanner.nextLine());
                        System.out.println("Digite a cidade do Hotel: ");
                        en.setCidade(scanner.nextLine());
                        System.out.println("Digite o estado do Hotel: ");
                        en.setEstado(scanner.nextLine());
                        h.setEndereco(en);
                        hotDAO.insert(h);
                        System.out.println("Hotel inserido!");
                    }
                break;
                case 3:
                    System.out.println("Digite o codigo do quarto que deseja inserir: ");
                    codigo = scanner.nextInt();
                    clearBuffer(scanner);
                    q = quaDAO.select(codigo);
                    if (q != null) {
                        System.out.println("Quarto ja existente, nao eh possivel inserir!");
                    } else {
                        q = new Quarto();
                        q.setCodigo(codigo);
                        System.out.println("Digite o andar: ");
                        q.setAndar(scanner.nextInt());
                        clearBuffer(scanner);
                        System.out.println("Digite o numero: ");
                        q.setNumero(scanner.nextInt());
                        clearBuffer(scanner);
                        System.out.println("Digite o codHotel: ");
                        q.setCodHotel(scanner.nextInt());
                        clearBuffer(scanner);
                        System.out.println("Ocupado: ");
                        q.setOcupado(scanner.nextLine());
                        t = new TipoQuarto();
                        System.out.println("Digite o tipo: ");
                        t.setTipo(scanner.nextLine());
                        System.out.println("Cama extra: ");
                        t.setCamaExtra(scanner.nextLine());
                        System.out.println("Digite o preco: ");
                        t.setPreco(scanner.nextInt());
                        clearBuffer(scanner);
                        q.setTipo(t);
                        quaDAO.insert(q);
                        System.out.println("Quarto inserido!");
                    }
                break;
                case 4:
                    System.out.println("Digite o codigo da reserva que deseja inserir: ");
                    codigo = scanner.nextInt();
                    clearBuffer(scanner);
                    r = new Reserva();
                    r = resDAO.select(codigo);
                    if (r != null) {
                        System.out.println("Reserva ja existente, nao eh possivel inserir!");
                    } else {
                        r = new Reserva();
                        r.setCodigo(codigo);
                        System.out.println("Digite o rg do cliente: ");
                        r.setCodCliente(scanner.nextInt());
                        clearBuffer(scanner);
                        System.out.println("Digite o tipoQuarto: ");
                        r.setTipoQuarto(scanner.nextLine());
                        System.out.println("Digite o codHotel: ");
                        r.setCodHotel(scanner.nextInt());
                        clearBuffer(scanner);
                        System.out.println("Digite a data de entrada (dia/mes/ano): ");
                        r.setDataEntrada(scanner.nextLine());
                        System.out.println("Digite a data de saida (dia/mes/ano): ");
                        r.setDataSaida(scanner.nextLine());
                        
                        q = new Quarto();
                        q = quaDAO.quartoLivre(r.getTipoQuarto(), r.getCodHotel());
                        if(q!=null){
                            es = new Estadia();
                            es.setCodigo(estDAO.codigoDisponivel());
                            es.setCodQuarto(q.getCodigo());
                            es.setDataEntrada(r.getDataEntrada());
                            es.setDataSaida(r.getDataSaida());
                            es.setCodReserva(r.getCodigo());
                            estDAO.insert(es);
                            r.setEstadia("sim");
                            System.out.println("Reserva inserida! Gerado estadia");
                            quaDAO.setQuartoOcupado(q);
                        }
                        else{
                            r.setEstadia("nao");
                            System.out.println("Reserva inserida! Estadia ainda nao gerada, sem quarto disponivel");
                        }
                        r.setAtivo("sim");
                        resDAO.insert(r);
                    }
                break;
                case 5:
                    System.out.println("Digite o codigo da Estadia que deseja inserir um servico extra: ");
                    codigo = scanner.nextInt();
                    es = new Estadia();
                    es = estDAO.select(codigo);
                    if (es == null) {
                        System.out.println("Nao existe extadia com esse codigo, nao eh possivel inserir extra!");
                    } else {
                        ex = new Extra();
                        ex.setCodigoEstadia(codigo);
                        System.out.println("Digite o codServico: ");
                        ex.setCodServico(scanner.nextInt());
                        clearBuffer(scanner);
                        System.out.println("Digite a data (dia/mes/ano): ");
                        ex.setData(scanner.nextLine());
                        System.out.println("Digite a hora (hora:minuto): ");
                        ex.setHora(scanner.nextLine());
                        extDAO.insert(ex);
                        System.out.println("Extra inserido!");
                    }
                break;
                    
                case 6:
                    System.out.println("Digite o rg do Cliente que deseja pesquisar: ");
                    rg = scanner.nextInt();
                    c = new Cliente();
                    c = cliDAO.select(rg);
                    if (c == null) {
                        System.out.println("Cliente nao encontrado ou inexistente!");
                    } else {
                        System.out.println(c);
                    }
                break;
                case 7:
                    List<Cliente> listaCli = cliDAO.selectAll();
                    for (Cliente cli : listaCli) {
                        System.out.println(cli);
                    }
                break;
                case 8:
                    System.out.println("Digite o codigio do Hotel que deseja pesquisar: ");
                    codigo = scanner.nextInt();
                    clearBuffer(scanner);
                    h = new Hotel();
                    h = hotDAO.select(codigo);
                    if (h == null) {
                        System.out.println("Hotel nao encontrado ou inexistente!");
                    } else {
                        System.out.println(h);
                    }
                break;
                case 9:
                    List<Hotel> listaHot = hotDAO.selectAll();
                    for (Hotel hot : listaHot) {
                        System.out.println(hot);
                    }
                break;
                case 10:
                    System.out.println("Digite o codigo do quarto que deseja pesquisar: ");
                    codigo = scanner.nextInt();
                    clearBuffer(scanner);
                    q = new Quarto();
                    q = quaDAO.select(codigo);
                    if (q == null) {
                        System.out.println("Quarto nao encontrado ou inexistente");
                    } else {
                        System.out.println(q);
                    }
                break;
                case 11:
                    List<Quarto> listaQua = quaDAO.selectAll();
                    for (Quarto qua : listaQua) {
                        System.out.println(qua);
                    }
                break;
                case 12:
                    System.out.println("Digite o codigo da reserva que deseja pesquisar: ");
                    codigo = scanner.nextInt();
                    clearBuffer(scanner);
                    r = new Reserva();
                    r = resDAO.select(codigo);
                    if (r == null) {
                        System.out.println("Reserva nao encontrada ou inexistente!");
                    } else {
                        System.out.println(r);
                    }
                break;
                case 13:
                    List<Reserva> listaRes = resDAO.selectAll();
                    for (Reserva res : listaRes) {
                        System.out.println(res);
                    }
                break;
                case 14:
                    System.out.println("Digite o codigo da estadia que deseja pesquisar: ");
                    codigo = scanner.nextInt();
                    clearBuffer(scanner);
                    es = new Estadia();
                    es = estDAO.select(codigo);
                    if (es == null) {
                        System.out.println("Estadia nao encontrada ou inexistente!");
                    } else {
                        System.out.println(es);
                    }
                break;
                case 15:
                    List<Estadia> listaEst = estDAO.selectAll();
                    for (Estadia est : listaEst) {
                        System.out.println(est);
                    }
                break;
                case 16:
                    List<Servico> listaSer = serDAO.selectAll();
                    for (Servico ser : listaSer) {
                        System.out.println(ser);
                    }
                break;
                case 17:
                    System.out.println("Digite o codigo da estadia que deseja pesquisar os extras: ");
                    codigo = scanner.nextInt();
                    clearBuffer(scanner);
                    List<Extra> listaExt = extDAO.select(codigo);
                    for (Extra ext : listaExt) {
                        System.out.println(ext);
                    }
                    break;
                case 18:
                    List<Extra> listaExtA = extDAO.selectAll();
                    for (Extra ext : listaExtA) {
                        System.out.println(ext);
                    }
                break;
                case 0:
                    System.out.println("Saindo!");
                break;
            }
            opcao = menu();
        }
        
        ///////////////////////////////////////
    }
    public static int menu(){
        System.out.println("\n");
        Scanner scanner = new Scanner(System.in);
        System.out.println("1 - Cadastrar novo Cliente");
        System.out.println("2 - Cadastrar novo Hotel");
        System.out.println("3 - Cadastrar novo Quarto");
        System.out.println("4 - Fazer reserva");
        System.out.println("5 - Cdastrar um servico extra para uma estadia");
        System.out.println("6 - Pesquisar um cliente");
        System.out.println("7 - Mostrar todos os clientes");
        System.out.println("8 - Pesquisar um Hotel");
        System.out.println("9 - Mostrar todos os hoteis");
        System.out.println("10 - Pesquisar um quarto");
        System.out.println("11 - Mostrar todos os quartos");
        System.out.println("12 - Pesquisar uma reserva");
        System.out.println("13 - Mostrar todas as reservas");
        System.out.println("14 - Pesquisar uma estadia");
        System.out.println("15 - Mostrar todas as estadias");
        System.out.println("16 - Mostrar todos servicos oferecidos");
        System.out.println("17 - Pesquisar os extras requeridos de uma estadia");
        System.out.println("18 - Mostrar todos os extras de todas estadias");
        System.out.println("0 - Sair");
        System.out.print("Digite uma das operacoes a cima: ");
        int tabela = scanner.nextInt();
        while(tabela<0 || tabela>18){
            System.out.println("Opcao invalida, tente novamente!");
            System.out.print("Digite uma das operacoes a cima: ");
            tabela = scanner.nextInt();
        }
        System.out.println("\n");
        return tabela;
    }
    private static void clearBuffer(Scanner scanner) {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }
}
