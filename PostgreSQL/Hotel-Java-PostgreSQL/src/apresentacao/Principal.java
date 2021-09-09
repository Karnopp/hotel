
package apresentacao;
import dados.Cliente;
import dados.Endereco;
import dados.Estadia;
import dados.Extra;
import dados.Hotel;
import dados.Limpeza;
import dados.Quarto;
import dados.Reserva;
import dados.Servicos;
import dados.TipoQuarto;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;
import persistencia.ClienteDAO;
import persistencia.EnderecoDAO;
import persistencia.EstadiaDAO;
import persistencia.ExtraDAO;
import persistencia.HotelDAO;
import persistencia.LimpezaDAO;
import persistencia.QuartoDAO;
import persistencia.ReservaDAO;
import persistencia.ServicosDAO;
import persistencia.TipoQuartoDAO;

public class Principal {
    public static void main(String[] args){
        try {
            Scanner scanner = new Scanner(System.in);
            HotelDAO hotDAO = HotelDAO.getInstance();
            ClienteDAO cliDAO = ClienteDAO.getInstance();
            EnderecoDAO endDAO = EnderecoDAO.getInstance();
            QuartoDAO quaDAO = QuartoDAO.getInstance();
            ReservaDAO resDAO = ReservaDAO.getInstance();
            EstadiaDAO estDAO = EstadiaDAO.getInstance();
            ServicosDAO serDAO = ServicosDAO.getInstance();
            LimpezaDAO limDAO = LimpezaDAO.getInstance();
            TipoQuartoDAO tipDAO = TipoQuartoDAO.getInstance();
            ExtraDAO extDAO = ExtraDAO.getInstance();
            int tabela, opcao, codigo;
            String nome;
            System.out.println("Bem vindo!");
            tabela=menuTabela();
            while(tabela!=0){
                switch(tabela){
                    case 1:
                        Hotel h;
                        opcao=menuOp();                       
                        while(opcao!=0){
                            switch(opcao){
                                case 1:
                                    System.out.println("Qual o codigo do Hotel que gostaria de pesquisar? ");
                                    codigo = scanner.nextInt();
                                    clearBuffer(scanner);
                                    h = hotDAO.select(codigo);
                                    if (h != null) {
                                        System.out.println(h);
                                    } else {
                                        System.out.println("Hotel nao encontrado");
                                    }
                                    opcao=menuOp();
                                break;
                                case 2:
                                    System.out.println("Qual o codigo do Hotel que gostaria de deletar? ");
                                    codigo = scanner.nextInt();
                                    clearBuffer(scanner);
                                    h = hotDAO.select(codigo);
                                    if (h != null) {
                                        hotDAO.delete(codigo);
                                        System.out.println("Hotel excluido!");
                                    } else {
                                        System.out.println("Hotel nao encontrado!");
                                    }
                                    opcao=menuOp();
                                break;
                                case 3:
                                    System.out.println("Qual o codigo do Hotel que gostaria de atualizar? ");
                                    codigo = scanner.nextInt();
                                    clearBuffer(scanner);
                                    h = hotDAO.select(codigo);
                                    if (h != null) {
                                        System.out.println("Digite o nome do Hotel: ");
                                        h.setNome(scanner.nextLine());
                                        System.out.println("Digite o telefone do Hotel: ");
                                        h.setTelefone(scanner.nextLine());
                                        System.out.println("Digite o codEndereco do Hotel: ");
                                        h.setCodEndereco(scanner.nextInt());
                                        clearBuffer(scanner);
                                        hotDAO.update(h);
                                        System.out.println("Hotel atualizado!");
                                    } else {
                                        System.out.println("Hotel nao encontrado!");
                                    }
                                    opcao=menuOp();
                                break;
                                case 4:
                                    System.out.println("Digite o codigio do Hotel que deseja inserir: ");
                                    codigo = scanner.nextInt();
                                    clearBuffer(scanner);
                                    h = hotDAO.select(codigo);
                                    if (h != null) {
                                        System.out.println("Hotel ja existente, nao eh possivel inserir!");
                                    } else {
                                        Hotel h1 = new Hotel();
                                        h1.setCodigo(codigo);
                                        System.out.println("Digite o nome do Hotel: ");
                                        h1.setNome(scanner.nextLine());
                                        System.out.println("Digite o telefone do Hotel: ");
                                        h1.setTelefone(scanner.nextLine());
                                        System.out.println("Digite o codEndereco do Hotel: ");
                                        h1.setCodEndereco(scanner.nextInt());
                                        clearBuffer(scanner);
                                        hotDAO.insert(h1);
                                        System.out.println("Hotel inserido!");
                                    }
                                    opcao=menuOp();
                                break;
                                case 5:
                                    List<Hotel> listaHot = hotDAO.selectAll();
                                    for (Hotel hot : listaHot) {
                                        System.out.println(hot);
                                    }
                                    opcao=menuOp();
                                break;
                            }
                        }
                    break;
                    case 2:
                        Cliente c;
                        opcao=menuOp();                       
                        while(opcao!=0){
                            switch(opcao){
                                case 1:
                                    System.out.println("Qual o rg do Cliente que gostaria de pesquisar? ");
                                    c = cliDAO.select(scanner.nextLine());
                                    if (c != null) {
                                        System.out.println(c);
                                    } else {
                                        System.out.println("Cliente nao encontrado");
                                    }
                                    opcao=menuOp();
                                break;
                                case 2:
                                    System.out.println("Qual o rg do Cliente que gostaria de deletar? ");
                                    c = cliDAO.select(scanner.nextLine());
                                    if (c != null) {
                                        cliDAO.delete(c.getRg());
                                        System.out.println("Cliente excluido!");
                                    } else {
                                        System.out.println("Cliente nao encontrado!");
                                    }
                                    opcao=menuOp();
                                break;
                                case 3:
                                    System.out.println("Qual o rg do Cliente que gostaria de atualizar? ");
                                    c = cliDAO.select(scanner.nextLine());
                                    if (c != null) {
                                        System.out.println("Digite o nome do Cliente: ");
                                        c.setNome(scanner.nextLine());
                                        System.out.println("Digite o telefone do Cliente: ");
                                        c.setTelefone(scanner.nextLine());
                                        System.out.println("Digite o codEndereco do Cliente: ");
                                        c.setCodEndereco(scanner.nextInt());
                                        clearBuffer(scanner);
                                        cliDAO.update(c);
                                        System.out.println("Cliente atualizado!");
                                    } else {
                                        System.out.println("Cliente nao encontrado!");
                                    }
                                    opcao=menuOp();
                                break;
                                case 4:
                                    System.out.println("Digite o rg do Cliente que deseja inserir: ");
                                    String rg = scanner.nextLine();
                                    c = cliDAO.select(rg);
                                    if (c != null) {
                                        System.out.println("Cliente ja existente, nao eh possivel inserir!");
                                    } else {
                                        Cliente c1 = new Cliente();
                                        c1.setRg(rg);
                                        System.out.println("Digite o nome do Cliente: ");
                                        c1.setNome(scanner.nextLine());
                                        System.out.println("Digite o telefone do Cliente: ");
                                        c1.setTelefone(scanner.nextLine());
                                        System.out.println("Digite o codEndereco do Cliente: ");
                                        c1.setCodEndereco(scanner.nextInt());
                                        clearBuffer(scanner);
                                        cliDAO.insert(c1);
                                        System.out.println("Cliente inserido!");
                                    }
                                    opcao=menuOp();
                                break;
                                case 5:
                                    List<Cliente> listaCli = cliDAO.selectAll();
                                    for (Cliente cli : listaCli) {
                                        System.out.println(cli);
                                    }
                                    opcao=menuOp();
                                break;
                            }
                        }
                    break;
                    case 3:
                        Endereco e;
                        opcao=menuOp();                       
                        while(opcao!=0){
                            switch(opcao){
                                case 1:
                                    System.out.println("Qual o codigo do endereco que gostaria de pesquisar? ");
                                    e = endDAO.select(scanner.nextInt());
                                    clearBuffer(scanner);
                                    if (e != null) {
                                        System.out.println(e);
                                    } else {
                                        System.out.println("Endereco nao encontrado");
                                    }
                                    opcao=menuOp();
                                break;
                                case 2:
                                    System.out.println("Qual o codigo do endereco que gostaria de deletar? ");
                                    e = endDAO.select(scanner.nextInt());
                                    clearBuffer(scanner);
                                    if (e != null) {
                                        endDAO.delete(e.getCodigo());
                                        System.out.println("Endereco excluido!");
                                    } else {
                                        System.out.println("Endereco nao encontrado!");
                                    }
                                    opcao=menuOp();
                                break;
                                case 3:
                                    System.out.println("Qual o codigo do endereco que gostaria de atualizar? ");
                                    e = endDAO.select(scanner.nextInt());
                                    clearBuffer(scanner);
                                    if (e != null) {
                                        System.out.println("Digite o nome da rua: ");
                                        e.setRua(scanner.nextLine());
                                        System.out.println("Digite o nome do bairro: ");
                                        e.setBairro(scanner.nextLine());
                                        System.out.println("Digite o nome da cidade: ");
                                        e.setCidade(scanner.nextLine());
                                        System.out.println("Digite o nome do estado: ");
                                        e.setEstado(scanner.nextLine());
                                        endDAO.update(e);
                                        System.out.println("Endereco atualizado!");
                                    } else {
                                        System.out.println("Endereco nao encontrado!");
                                    }
                                    opcao=menuOp();
                                break;
                                case 4:
                                    System.out.println("Digite o codigo do endereco que deseja inserir: ");
                                    codigo = scanner.nextInt();
                                    clearBuffer(scanner);
                                    e = endDAO.select(codigo);
                                    if (e != null) {
                                        System.out.println("Endereco ja existente, nao eh possivel inserir!");
                                    } else {
                                        Endereco e1 = new Endereco();
                                        e1.setCodigo(codigo);
                                        System.out.println("Digite o nome da rua: ");
                                        e1.setRua(scanner.nextLine());
                                        System.out.println("Digite o nome do bairro: ");
                                        e1.setBairro(scanner.nextLine());
                                        System.out.println("Digite o nome da cidade: ");
                                        e1.setCidade(scanner.nextLine());
                                        System.out.println("Digite o nome do estado: ");
                                        e1.setEstado(scanner.nextLine());
                                        endDAO.insert(e1);
                                        System.out.println("Endereco inserido!");
                                    }
                                    opcao=menuOp();
                                break;
                                case 5:
                                    List<Endereco> listaEnd = endDAO.selectAll();
                                    for (Endereco end : listaEnd) {
                                        System.out.println(end);
                                    }
                                    opcao=menuOp();
                                break;
                            }
                        }
                    break;
                    case 4:
                        Quarto q;
                        opcao=menuOp();                       
                        while(opcao!=0){
                            switch(opcao){
                                case 1:
                                    System.out.println("Qual o codigo do quarto que gostaria de pesquisar? ");
                                    q = quaDAO.select(scanner.nextInt());
                                    clearBuffer(scanner);
                                    if (q != null) {
                                        System.out.println(q);
                                    } else {
                                        System.out.println("Quarto nao encontrado");
                                    }
                                    opcao=menuOp();
                                break;
                                case 2:
                                    System.out.println("Qual o codigo do quarto que gostaria de deletar? ");
                                    q = quaDAO.select(scanner.nextInt());
                                    clearBuffer(scanner);
                                    if (q != null) {
                                        quaDAO.delete(q.getCodigo());
                                        System.out.println("Quarto excluido!");
                                    } else {
                                        System.out.println("Quarto nao encontrado!");
                                    }
                                    opcao=menuOp();
                                break;
                                case 3:
                                    System.out.println("Qual o codigo do quarto que gostaria de atualizar? ");
                                    q = quaDAO.select(scanner.nextInt());
                                    clearBuffer(scanner);
                                    if (q != null) {
                                        System.out.println("Digite o tipoQuarto: ");
                                        q.setTipoQuarto(scanner.nextInt());
                                        clearBuffer(scanner);
                                        System.out.println("Digite o andar: ");
                                        q.setAndar(scanner.nextInt());
                                        clearBuffer(scanner);
                                        System.out.println("Digite o numero: ");
                                        q.setNumero(scanner.nextInt());
                                        clearBuffer(scanner);
                                        System.out.println("Digite o codHotel: ");
                                        q.setCodHotel(scanner.nextInt());
                                        clearBuffer(scanner);
                                        quaDAO.update(q);
                                        System.out.println("Quarto atualizado!");
                                    } else {
                                        System.out.println("Quarto nao encontrado!");
                                    }
                                    opcao=menuOp();
                                break;
                                case 4:
                                    System.out.println("Digite o codigo do quarto que deseja inserir: ");
                                    codigo = scanner.nextInt();
                                    clearBuffer(scanner);
                                    q = quaDAO.select(codigo);
                                    if (q != null) {
                                        System.out.println("Quarto ja existente, nao eh possivel inserir!");
                                    } else {
                                        Quarto q1 = new Quarto();
                                        q1.setCodigo(codigo);
                                        System.out.println("Digite o tipoQuarto: ");
                                        q1.setTipoQuarto(scanner.nextInt());
                                        clearBuffer(scanner);
                                        System.out.println("Digite o andar: ");
                                        q1.setAndar(scanner.nextInt());
                                        clearBuffer(scanner);
                                        System.out.println("Digite o numero: ");
                                        q1.setNumero(scanner.nextInt());
                                        clearBuffer(scanner);
                                        System.out.println("Digite o codHotel: ");
                                        q1.setCodHotel(scanner.nextInt());
                                        clearBuffer(scanner);
                                        quaDAO.insert(q1);
                                        System.out.println("Quarto inserido!");
                                    }
                                    opcao=menuOp();
                                break;
                                case 5:
                                    List<Quarto> listaQua = quaDAO.selectAll();
                                    for (Quarto qua : listaQua) {
                                        System.out.println(qua);
                                    }
                                    opcao=menuOp();
                                break;
                            }
                        }
                    break;
                    case 5:
                        Reserva r;
                        opcao=menuOp();                       
                        while(opcao!=0){
                            switch(opcao){
                                case 1:
                                    System.out.println("Qual o codigo da reserva que gostaria de pesquisar? ");
                                    r = resDAO.select(scanner.nextInt());
                                    clearBuffer(scanner);
                                    if (r != null) {
                                        System.out.println(r);
                                    } else {
                                        System.out.println("Reserva nao encontrada");
                                    }
                                    opcao=menuOp();
                                break;
                                case 2:
                                    System.out.println("Qual o codigo da reserva que gostaria de deletar? ");
                                    r = resDAO.select(scanner.nextInt());
                                    clearBuffer(scanner);
                                    if (r != null) {
                                        resDAO.delete(r.getCodigo());
                                        System.out.println("Reserva excluida!");
                                    } else {
                                        System.out.println("Reserva nao encontrada!");
                                    }
                                    opcao=menuOp();
                                break;
                                case 3:
                                    System.out.println("Qual o codigo da reserva que gostaria de atualizar? ");
                                    r = resDAO.select(scanner.nextInt());
                                    clearBuffer(scanner);
                                    if (r != null) {
                                        System.out.println("Digite o rg do cliente: ");
                                        r.setCodCliente(scanner.nextInt());
                                        clearBuffer(scanner);
                                        System.out.println("Digite o tipoQuarto: ");
                                        r.setTipoQuarto(scanner.nextInt());
                                        clearBuffer(scanner);                                        
                                        System.out.println("Digite o codHotel: ");
                                        r.setCodHotel(scanner.nextInt());
                                        clearBuffer(scanner);
                                        System.out.println("Digite a data de entrada (dia/mes/ano): ");
                                        String sData = scanner.nextLine();
                                        java.sql.Date data = converteData(sData);
                                        r.setDataEntrada(data);
                                        System.out.println("Digite a data de saida (dia/mes/ano): ");
                                        sData = scanner.nextLine();
                                        data = converteData(sData);
                                        r.setDataSaida(data);
                                        System.out.println("Digite se aceita (sim ou nao): ");
                                        r.setAceita(scanner.nextLine());
                                        resDAO.update(r);
                                        System.out.println("Reserva atualizada!");
                                    } else {
                                        System.out.println("Reserva nao encontrada!");
                                    }
                                    opcao=menuOp();
                                break;
                                case 4:
                                    System.out.println("Digite o codigo da reserva que deseja inserir: ");
                                    codigo = scanner.nextInt();
                                    clearBuffer(scanner);
                                    r = resDAO.select(codigo);
                                    if (r != null) {
                                        System.out.println("Reserva ja existente, nao eh possivel inserir!");
                                    } else {
                                        Reserva r1 = new Reserva();
                                        r1.setCodigo(codigo);
                                        System.out.println("Digite o rg do cliente: ");
                                        r1.setCodCliente(scanner.nextInt());
                                        clearBuffer(scanner);
                                        System.out.println("Digite o tipoQuarto: ");
                                        r1.setTipoQuarto(scanner.nextInt());
                                        clearBuffer(scanner);                                        
                                        System.out.println("Digite o codHotel: ");
                                        r1.setCodHotel(scanner.nextInt());
                                        clearBuffer(scanner);
                                        System.out.println("Digite a data de entrada (dia/mes/ano): ");
                                        String sData = scanner.nextLine();
                                        java.sql.Date data = converteData(sData);
                                        r1.setDataEntrada(data);
                                        System.out.println("Digite a data de saida (dia/mes/ano): ");
                                        sData = scanner.nextLine();
                                        data = converteData(sData);
                                        r1.setDataSaida(data);
                                        System.out.println("Digite se aceita (sim ou nao): ");
                                        r1.setAceita(scanner.nextLine());
                                        resDAO.insert(r1);
                                        System.out.println("Reserva inserida!");
                                    }
                                    opcao=menuOp();
                                break;
                                case 5:
                                    List<Reserva> listaRes = resDAO.selectAll();
                                    for (Reserva res : listaRes) {
                                        System.out.println(res);
                                    }
                                    opcao=menuOp();
                                break;
                            }
                        }
                    break;
                    case 6:
                        Estadia es;
                        opcao=menuOp();                       
                        while(opcao!=0){
                            switch(opcao){
                                case 1:
                                    System.out.println("Qual o codigo da estadia que gostaria de pesquisar? ");
                                    es = estDAO.select(scanner.nextInt());
                                    clearBuffer(scanner);
                                    if (es != null) {
                                        System.out.println(es);
                                    } else {
                                        System.out.println("Estadia nao encontrada");
                                    }
                                    opcao=menuOp();
                                break;
                                case 2:
                                    System.out.println("Qual o codigo da estadia que gostaria de deletar? ");
                                    es = estDAO.select(scanner.nextInt());
                                    clearBuffer(scanner);
                                    if (es != null) {
                                        estDAO.delete(es.getCodigo());
                                        System.out.println("Estadia excluida!");
                                    } else {
                                        System.out.println("Estadia nao encontrada!");
                                    }
                                    opcao=menuOp();
                                break;
                                case 3:
                                    System.out.println("Qual o codigo da estadia que gostaria de atualizar? ");
                                    es = estDAO.select(scanner.nextInt());
                                    clearBuffer(scanner);
                                    if (es != null) {
                                        System.out.println("Digite o codQuarto: ");
                                        es.setCodQuarto(scanner.nextInt());
                                        clearBuffer(scanner);                                  
                                        System.out.println("Digite a data de entrada (dia/mes/ano): ");
                                        String sData = scanner.nextLine();
                                        java.sql.Date data = converteData(sData);
                                        es.setDataEntrada(data);
                                        System.out.println("Digite a data de saida (dia/mes/ano): ");
                                        sData = scanner.nextLine();
                                        data = converteData(sData);
                                        es.setDataSaida(data);
                                        System.out.println("Digite o codReserva: ");
                                        es.setCodReserva(scanner.nextInt());
                                        clearBuffer(scanner); 
                                        estDAO.update(es);
                                        System.out.println("Estadia atualizada!");
                                    } else {
                                        System.out.println("Estadia nao encontrada!");
                                    }
                                    opcao=menuOp();
                                break;
                                case 4:
                                    System.out.println("Digite o codigo da estadia que deseja inserir: ");
                                    codigo = scanner.nextInt();
                                    clearBuffer(scanner);
                                    es = estDAO.select(codigo);
                                    if (es != null) {
                                        System.out.println("Estadia ja existente, nao eh possivel inserir!");
                                    } else {
                                        Estadia es1 = new Estadia();
                                        es1.setCodigo(codigo);
                                        System.out.println("Digite o codQuarto: ");
                                        es1.setCodQuarto(scanner.nextInt());
                                        clearBuffer(scanner);                                  
                                        System.out.println("Digite a data de entrada (dia/mes/ano): ");
                                        String sData = scanner.nextLine();
                                        java.sql.Date data = converteData(sData);
                                        es1.setDataEntrada(data);
                                        System.out.println("Digite a data de saida (dia/mes/ano): ");
                                        sData = scanner.nextLine();
                                        data = converteData(sData);
                                        es1.setDataSaida(data);
                                        System.out.println("Digite o codReserva: ");
                                        es1.setCodReserva(scanner.nextInt());
                                        clearBuffer(scanner); 
                                        estDAO.insert(es1);
                                        System.out.println("Estadia inserida!");
                                    }
                                    opcao=menuOp();
                                break;
                                case 5:
                                    List<Estadia> listaEst = estDAO.selectAll();
                                    for (Estadia est : listaEst) {
                                        System.out.println(est);
                                    }
                                    opcao=menuOp();
                                break;
                            }
                        }
                    break;
                    case 7:
                        Servicos s;
                        opcao=menuOp();                       
                        while(opcao!=0){
                            switch(opcao){
                                case 1:
                                    System.out.println("Qual o codigo do servico que gostaria de pesquisar? ");
                                    s = serDAO.select(scanner.nextInt());
                                    clearBuffer(scanner);
                                    if (s != null) {
                                        System.out.println(s);
                                    } else {
                                        System.out.println("Servico nao encontrado");
                                    }
                                    opcao=menuOp();
                                break;
                                case 2:
                                    System.out.println("Qual o codigo do servico que gostaria de deletar? ");
                                    s = serDAO.select(scanner.nextInt());
                                    clearBuffer(scanner);
                                    if (s != null) {
                                        serDAO.delete(s.getCodigo());
                                        System.out.println("Servico excluido!");
                                    } else {
                                        System.out.println("Servico nao encontrado!");
                                    }
                                    opcao=menuOp();
                                break;
                                case 3:
                                    System.out.println("Qual o codigo do servico que gostaria de atualizar? ");
                                    s = serDAO.select(scanner.nextInt());
                                    clearBuffer(scanner);
                                    if (s != null) {
                                        System.out.println("Digite o tipo: ");
                                        s.setTipo(scanner.nextLine());                                  
                                        System.out.println("Digite o preco: ");
                                        s.setPreco(scanner.nextInt());
                                        clearBuffer(scanner); 
                                        serDAO.update(s);
                                        System.out.println("Servico atualizado!");
                                    } else {
                                        System.out.println("Servico nao encontrado!");
                                    }
                                    opcao=menuOp();
                                break;
                                case 4:
                                    System.out.println("Digite o codigo do servico que deseja inserir: ");
                                    codigo = scanner.nextInt();
                                    clearBuffer(scanner);
                                    s = serDAO.select(codigo);
                                    if (s != null) {
                                        System.out.println("Servico ja existente, nao eh possivel inserir!");
                                    } else {
                                        Servicos s1 = new Servicos();
                                        s1.setCodigo(codigo);
                                        System.out.println("Digite o tipo: ");
                                        s1.setTipo(scanner.nextLine());                                  
                                        System.out.println("Digite o preco: ");
                                        s1.setPreco(scanner.nextInt());
                                        clearBuffer(scanner); 
                                        serDAO.insert(s1);
                                        System.out.println("Servico inserido!");
                                    }
                                    opcao=menuOp();
                                break;
                                case 5:
                                    List<Servicos> listaSer = serDAO.selectAll();
                                    for (Servicos ser : listaSer) {
                                        System.out.println(ser);
                                    }
                                    opcao=menuOp();
                                break;
                            }
                        }
                    break;
                    case 8:
                        Limpeza l;
                        opcao=menuOp();                       
                        while(opcao!=0){
                            switch(opcao){
                                case 1:
                                    System.out.println("Qual a data da limpeza que gostaria de pesquisar? (dia/mes/ano) ");
                                    String sData = scanner.nextLine();
                                    java.sql.Date data = converteData(sData);
                                    l = limDAO.select(data);                               
                                    if (l != null) {
                                        System.out.println(l);
                                    } else {
                                        System.out.println("Limpeza nao encontrada");
                                    }
                                    opcao=menuOp();
                                break;
                                case 2:
                                    System.out.println("Qual a data da limpeza que gostaria de deletar? (dia/mes/ano) ");
                                    sData = scanner.nextLine();
                                    data = converteData(sData);
                                    l = limDAO.select(data);
                                    if (l != null) {
                                        limDAO.delete(l.getData());
                                        System.out.println("Limpeza excluida!");
                                    } else {
                                        System.out.println("Limpeza nao encontrada!");
                                    }
                                    opcao=menuOp();
                                break;
                                case 3:
                                    System.out.println("Qual a data da limpeza que gostaria de atualizar? ");
                                    sData = scanner.nextLine();
                                    data = converteData(sData);
                                    l = limDAO.select(data);
                                    if (l != null) {
                                        System.out.println("Digite o codQuarto: ");
                                        l.setCodQuarto(scanner.nextInt()); 
                                        clearBuffer(scanner); 
                                        System.out.println("Digite o rg do empregado: ");
                                        l.setCodEmpregado(scanner.nextLine());                                        
                                        limDAO.update(l);
                                        System.out.println("Limpeza atualizada!");
                                    } else {
                                        System.out.println("Limpeza nao encontrada!");
                                    }
                                    opcao=menuOp();
                                break;
                                case 4:
                                    System.out.println("Digite a data da limpeza que deseja inserir (dia/mes/ano): ");
                                    sData = scanner.nextLine();
                                    data = converteData(sData);
                                    l = limDAO.select(data);
                                    if (l != null) {
                                        System.out.println("Limpeza ja existente, nao eh possivel inserir!");
                                    } else {
                                        Limpeza l1 = new Limpeza();
                                        l1.setData(data);
                                        System.out.println("Digite o codQuarto: ");
                                        l1.setCodQuarto(scanner.nextInt());
                                        clearBuffer(scanner);
                                        System.out.println("Digite o rg do empregado: ");
                                        l1.setCodEmpregado(scanner.nextLine());
                                        limDAO.insert(l1);
                                        System.out.println("Limpeza inserida!");
                                    }
                                    opcao=menuOp();
                                break;
                                case 5:
                                    List<Limpeza> listaLim = limDAO.selectAll();
                                    for (Limpeza lim : listaLim) {
                                        System.out.println(lim);
                                    }
                                    opcao=menuOp();
                                break;
                            }
                        }
                    break;
                    case 9:
                        TipoQuarto t;
                        opcao = menuOp();
                        while (opcao != 0) {
                            switch (opcao) {
                                case 1:
                                    System.out.println("Qual o codigo do tipoQuarto que gostaria de pesquisar? ");
                                    t = tipDAO.select(scanner.nextInt());
                                    clearBuffer(scanner);
                                    if (t != null) {
                                        System.out.println(t);
                                    } else {
                                        System.out.println("TipoQuarto nao encontrado");
                                    }
                                    opcao = menuOp();
                                    break;
                                case 2:
                                    System.out.println("Qual o codigo do tipoQuarto que gostaria de deletar? ");
                                    t = tipDAO.select(scanner.nextInt());
                                    clearBuffer(scanner);
                                    if (t != null) {
                                        tipDAO.delete(t.getCodigo());
                                        System.out.println("TipoQuarto excluido!");
                                    } else {
                                        System.out.println("TipoQuarto nao encontrado!");
                                    }
                                    opcao = menuOp();
                                    break;
                                case 3:
                                    System.out.println("Qual o codigo do tipoQuarto que gostaria de atualizar? ");
                                    t = tipDAO.select(scanner.nextInt());
                                    clearBuffer(scanner);
                                    if (t != null) {
                                        System.out.println("Digite o tipo: ");
                                        t.setTipo(scanner.nextLine());
                                        System.out.println("Digite o preco: ");
                                        t.setPreco(scanner.nextInt());
                                        clearBuffer(scanner);
                                        System.out.println("Digite se tem cama extra (sim/nao): ");
                                        t.setCamaExtra(scanner.nextLine());
                                        tipDAO.update(t);
                                        System.out.println("TipoQuarto atualizado!");
                                    } else {
                                        System.out.println("TipoQuarto nao encontrado!");
                                    }
                                    opcao = menuOp();
                                    break;
                                case 4:
                                    System.out.println("Digite o codigo do tipoQuarto que deseja inserir: ");
                                    codigo = scanner.nextInt();
                                    clearBuffer(scanner);
                                    t = tipDAO.select(codigo);
                                    if (t != null) {
                                        System.out.println("TipoQuarto ja existente, nao eh possivel inserir!");
                                    } else {
                                        TipoQuarto t1 = new TipoQuarto();
                                        t1.setCodigo(codigo);
                                        System.out.println("Digite o tipo: ");
                                        t1.setTipo(scanner.nextLine());
                                        System.out.println("Digite o preco: ");
                                        t1.setPreco(scanner.nextInt());
                                        clearBuffer(scanner);
                                        System.out.println("Digite se tem cama extra (sim/nao): ");
                                        t1.setCamaExtra(scanner.nextLine());
                                        tipDAO.insert(t1);
                                        System.out.println("Servico inserido!");
                                    }
                                    opcao = menuOp();
                                    break;
                                case 5:
                                    List<TipoQuarto> listaTip = tipDAO.selectAll();
                                    for (TipoQuarto tip : listaTip) {
                                        System.out.println(tip);
                                    }
                                    opcao = menuOp();
                                    break;
                            }
                        }
                        break;
                        case 10:
                        Extra ex;
                        opcao = menuOp();
                        while (opcao != 0) {
                            switch (opcao) {
                                case 1:
                                    System.out.println("Qual o codigoEstadia do extra que gostaria de pesquisar? ");
                                    List<Extra> listaExtraEstadia = extDAO.select(scanner.nextInt());
                                    clearBuffer(scanner);
                                    if(listaExtraEstadia == null){
                                        System.out.println("Extra nao encontrado");
                                    }
                                    else{
                                        for (Extra ext : listaExtraEstadia) {
                                            System.out.println(ext);
                                        }
                                    }                                    
                                    opcao = menuOp();
                                    break;
                                case 2:
                                    Extra ext = new Extra();
                                    System.out.println("Qual o codigoEstadia do extra que gostaria de deletar? ");
                                    ext.setCodEstadia(scanner.nextInt());
                                    clearBuffer(scanner);
                                    System.out.println("Qual o codigoServico do extra que gostaria de deletar? ");
                                    ext.setCodServico(scanner.nextInt());
                                    clearBuffer(scanner);
                                    System.out.println("Qual a data do extra que gostaria de deletar? (dia/mes/ano) ");
                                    String sData = scanner.nextLine();
                                    Date data = converteData(sData);
                                    ext.setData(data);
                                    System.out.println("Qual a hora do extra que gostaria de deletar? (hora:minuto) ");
                                    String sHora = scanner.nextLine();
                                    Time hora = converteHora(sHora);
                                    ext.setHora(hora);
                                    extDAO.delete(ext);
                                    System.out.println("Extra excluido! ");
                                    opcao = menuOp();
                                    break;
                                case 3:
                                    Extra extAntigo = new Extra();
                                    Extra extNovo = new Extra();
                                    System.out.println("Qual o codigoEstadia do extra que gostaria de atualizar? ");
                                    extAntigo.setCodEstadia(scanner.nextInt());
                                    clearBuffer(scanner);
                                    System.out.println("Qual o codigoServico do extra que gostaria de atualizar? ");
                                    extAntigo.setCodServico(scanner.nextInt());
                                    clearBuffer(scanner);
                                    System.out.println("Qual a data do extra que gostaria de atualizar? (dia/mes/ano) ");
                                    sData = scanner.nextLine();
                                    data = converteData(sData);
                                    extAntigo.setData(data);
                                    System.out.println("Qual a hora do extra que gostaria de atualizar? (hora:minuto) ");
                                    sHora = scanner.nextLine();
                                    hora = converteHora(sHora);
                                    extAntigo.setHora(hora);
                                    
                                    System.out.println("Qual o novo codigoServico do extra que gostaria de atualizar? ");
                                    extNovo.setCodServico(scanner.nextInt());
                                    clearBuffer(scanner);
                                    System.out.println("Qual a nova data do extra que gostaria de atualizar? (dia/mes/ano) ");
                                    sData = scanner.nextLine();
                                    data = converteData(sData);
                                    extNovo.setData(data);
                                    System.out.println("Qual a nova hora do extra que gostaria de atualizar? (hora:minuto) ");
                                    sHora = scanner.nextLine();
                                    hora = converteHora(sHora);
                                    extNovo.setHora(hora);                                    
                                    
                                    extDAO.update(extAntigo, extNovo);
                                    System.out.println("Extra atualizado!");
                                    opcao = menuOp();
                                    break;
                                case 4:
                                    Extra ex1 = new Extra();
                                    System.out.println("Digite o codEstadia do extra que deseja inserir: ");                                    
                                    ex1.setCodEstadia(scanner.nextInt());
                                    clearBuffer(scanner);
                                    System.out.println("Digite o codServico do extra que deseja inserir: ");                                    
                                    ex1.setCodServico(scanner.nextInt());
                                    clearBuffer(scanner);
                                    System.out.println("Digite a data do extra que deseja inserir (dia/mes/ano): ");                                    
                                    sData = scanner.nextLine();
                                    data = converteData(sData);
                                    ex1.setData(data);
                                    System.out.println("Digite a hora do extra que deseja inserir (hora:minuto): ");
                                    sHora = scanner.nextLine();
                                    hora = converteHora(sHora);
                                    ex1.setHora(hora);
                                    extDAO.insert(ex1);
                                    opcao = menuOp();
                                    break;
                                case 5:
                                    List<Extra> listaExt = extDAO.selectAll();
                                    for (Extra extra : listaExt) {
                                        System.out.println(extra);
                                    }
                                    opcao = menuOp();
                                    break;
                            }
                        }
                        break;
                }
                tabela=menuTabela();
            }
            System.out.println("Saindo!");
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        
    }
    public static int menuOp(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("1 - Select");
        System.out.println("2 - Delete");
        System.out.println("3 - Update");
        System.out.println("4 - Insert");
        System.out.println("5 - SelectAll");
        System.out.println("0 - Cancelar");
        System.out.print("Qual opcao deseja realizar? ");
        int op = scanner.nextInt();
        while(op<0 || op>5){
            System.out.println("Opcao invalida, tente novamente!");
            System.out.print("Qual opcao deseja realizar? ");
            op = scanner.nextInt();
        }
        return op;
    }
    
    public static int menuTabela(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("1 - Hotel");
        System.out.println("2 - Cliente");
        System.out.println("3 - Endereco");
        System.out.println("4 - Quarto");
        System.out.println("5 - Reserva");
        System.out.println("6 - Estadia");
        System.out.println("7 - Servicos");
        System.out.println("8 - Limpeza");
        System.out.println("9 - TipoQuarto");
        System.out.println("10 - Extra");
        System.out.println("0 - Sair");
        System.out.print("Qual tabela deseja realizar operacoes? ");
        int tabela = scanner.nextInt();
        while(tabela<0 || tabela>10){
            System.out.println("Opcao invalida, tente novamente!");
            System.out.print("Qual tabela deseja realizar operacoes? ");
            tabela = scanner.nextInt();
        }
        return tabela;
    }
    
    private static void clearBuffer(Scanner scanner) {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }
    
    public static Date converteData(String stringData){
        java.sql.Date sql = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            sql = new java.sql.Date(format.parse(stringData).getTime());

        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return sql;
    }
    
    public static Time converteHora(String stringHora){
        java.sql.Time sql = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            sql = new java.sql.Time(format.parse(stringHora).getTime());

        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return sql;
    }
}
