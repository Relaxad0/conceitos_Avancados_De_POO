import java.io.*;

class Calculadora {
    public static double dividir(double a, double b) {
        if (b == 0) throw new ArithmeticException("Divisão por zero não é permitida.");
        return a / b;
    }
}

class ConversorTemperatura {
    public static double converterCelsiusParaFahrenheit(double temperaturaCelsius) {
        if (temperaturaCelsius < -273.15) {
            throw new IllegalArgumentException("Temperatura abaixo do zero absoluto!");
        }
        return (temperaturaCelsius * 9 / 5) + 32;
    }
}

class Idade {
    public static void verificarIdade(int idade) {
        if (idade < 0 || idade > 150) {
            throw new IllegalArgumentException("Idade inválida: " + idade);
        }
    }
}

class DivisaoPorZero {
    public static void executar() {
        try {
            int x = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("Não é possível dividir por zero!");
        }
    }
}

class LeitorArquivo {
    public static void lerArquivo(String caminho) {
        try {
            FileReader fr = new FileReader(caminho);
            BufferedReader br = new BufferedReader(fr);
            String linha;
            while ((linha = br.readLine()) != null) {
                System.out.println(linha);
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + caminho);
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo.");
        }
    }
}

class ConversorStringNumero {
    public static void converter(String valor) {
        try {
            int numero = Integer.parseInt(valor);
            System.out.println("Número convertido: " + numero);
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido! Digite um número inteiro.");
        }
    }
}

class SaldoInsuficienteException extends Exception {
    public SaldoInsuficienteException(String message) {
        super(message);
    }
}

class ContaBancaria {
    private String titular;
    private double saldo;

    public ContaBancaria(String titular, double saldoInicial) {
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    public void sacar(double quantia) throws SaldoInsuficienteException {
        if (quantia > saldo)
            throw new SaldoInsuficienteException(
                "Saldo insuficiente para " + titular + "! Saldo atual = " + saldo);
        saldo -= quantia;
    }

    public double getSaldo() {
        return saldo;
    }

    public String toString() {
        return "Conta de " + titular + " | Saldo: R$ " + saldo;
    }
}

class SenhaInvalidaException extends Exception {
    public SenhaInvalidaException(String message) {
        super(message);
    }
}

class ValidadorSenha {
    public static void validarSenha(String senha) throws SenhaInvalidaException {
        if (senha.length() < 6)
            throw new SenhaInvalidaException("Senha muito curta! Mínimo 6 caracteres.");
        if (!senha.matches(".*[A-Z].*"))
            throw new SenhaInvalidaException("Senha deve conter ao menos 1 letra maiúscula.");
        if (!senha.matches(".*[0-9].*"))
            throw new SenhaInvalidaException("Senha deve conter ao menos 1 número.");
    }
}

class TransferenciaInvalidaException extends Exception {
    public TransferenciaInvalidaException(String message) {
        super(message);
    }
}

class ContaBancariaTransferencia {
    private String titular;
    private double saldo;

    public ContaBancariaTransferencia(String titular, double saldoInicial) {
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    public void transferir(ContaBancariaTransferencia destino, double quantia)
            throws TransferenciaInvalidaException {
        if (quantia <= 0)
            throw new TransferenciaInvalidaException("Quantia inválida.");
        if (quantia > saldo)
            throw new TransferenciaInvalidaException(
                "Saldo insuficiente para " + titular + ". Saldo: R$ " + saldo);
        this.saldo -= quantia;
        destino.saldo += quantia;
    }

    public double getSaldo() {
        return saldo;
    }

    public String toString() {
        return "Conta de " + titular + " | Saldo: R$ " + saldo;
    }
}

class DivisaoInteiraInvalidaException extends Exception {
    public DivisaoInteiraInvalidaException(String message) {
        super(message);
    }
}

class DivisaoInteira {
    public static int dividir(int a, int b) throws DivisaoInteiraInvalidaException {
        if (b == 0) throw new DivisaoInteiraInvalidaException("Divisão por zero!");
        if (a % b != 0) throw new DivisaoInteiraInvalidaException("Divisão não é exata!");
        return a / b;
    }
}

public class exerciciosExcecoes {
    public static void main(String[] args) {

        try {
            System.out.println("Divisão: " + Calculadora.dividir(10, 2));
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("Fahrenheit: " + ConversorTemperatura.converterCelsiusParaFahrenheit(-300));
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        try {
            Idade.verificarIdade(200);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        DivisaoPorZero.executar();

        LeitorArquivo.lerArquivo("arquivo.txt");

        ConversorStringNumero.converter("abc");

        ContaBancaria conta = new ContaBancaria("Ana", 100);
        try {
            conta.sacar(150);
        } catch (SaldoInsuficienteException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        System.out.println(conta);

        try {
            ValidadorSenha.validarSenha("abc");
        } catch (SenhaInvalidaException e) {
            System.out.println("Senha inválida: " + e.getMessage());
        }

        ContaBancariaTransferencia a = new ContaBancariaTransferencia("João", 500);
        ContaBancariaTransferencia b = new ContaBancariaTransferencia("Maria", 100);
        try {
            a.transferir(b, 200);
            System.out.println("Transferência OK!");
        } catch (TransferenciaInvalidaException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        System.out.println(a);
        System.out.println(b);

        try {
            System.out.println("Resultado divisão inteira: " + DivisaoInteira.dividir(10, 3));
        } catch (DivisaoInteiraInvalidaException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}