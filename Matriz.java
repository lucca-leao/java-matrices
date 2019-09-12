package matriz;
/**
 *
 * @author Lucca Leão
 */
public class Matriz {
    private final int i; //linhas
    private final int j; //colunas
    private double[][] elementos;
    
    //construtor uma matriz de linhas x colunas
    public Matriz(int linhas, int colunas){
        this.i = linhas;
        this.j = colunas;
        elementos = new double[linhas][colunas];
    }
    
    //construtor 
    public Matriz(Matriz A){
        this.i = A.i;
        this.j = A.j;
        this.elementos = new double[i][j];
        for(int m = 0; m < this.i; m++){
            for(int n = 0; n < this.j; n++){
                this.elementos[m][n]=A.elementos[m][n];
            }
        }
    }
    
    //retorna os elementos como uma String em forma matricial
    public String toString(){
        String matriz_string = new String();
        for(int m = 0; m < this.i; m++){
            for(int n = 0; n < this.j; n++){
                matriz_string += this.elementos[m][n] + " ";
            }
            matriz_string += "\n";
        }
        return matriz_string;
    }

    //multiplica a matriz a matriz corrente por B e retorna uma Matriz C
    public Matriz mult(Matriz B) {
        int aRows = this.i;
        int aColumns = this.j;
        int bRows = B.i;
        int bColumns = B.j;
        if (aColumns != bRows) throw new RuntimeException("Operacao ilegal entre matrizes");
        Matriz C = new Matriz(aRows, bColumns);
        for(int m = 0; m < aRows; m++){
            for(int n = 0; n < bColumns; n++){
                for(int k = 0; k < aColumns; k++){
                    C.elementos[m][n] += this.elementos[m][k]*B.elementos[k][n];
                }
            }
        }
        return C;
}

    //inicializa matriz com zeros
    public Matriz zeros(){
        int x, y;
        for(x = 0; x< i; x++){
            for(y = 0; y < j; y++){
                elementos[x][y] = 0;
            }
        }
        return this;
    }
    //inicializa matriz com 1s
        public Matriz ones(){
        int x, y;
        for(x = 0; x< i; x++){
            for(y = 0; y < j; y++){
                elementos[x][y] = 1;
            }
        }
        return this;
    }
    
    //inicializa matriz identidade
    public Matriz unit(){
        int x, y;
        for(x = 0; x < i; x++){
            for(y = 0; y < j; y++){
                if(x == y){
                    elementos[x][y]=1;
                }
                else
                    elementos[x][y]=0;
            }
        }
        return this;
    }
    
    //retorna a matriz transposta
    public Matriz transposta(){
        Matriz C = new Matriz(this.i,this.j);
        for(int m = 0; m < this.i; m++){
            for(int n = 0; n < this.j; n++){
                C.elementos[n][m]=this.elementos[m][n];
            }
        }
        return C;
    }
    
    //soma duas matrizes
    public Matriz soma(Matriz B){
        if((B.i != this.i) || (B.j != this.j))  throw new RuntimeException("Operacao ilegal entre matrizes");
        Matriz C = new Matriz(this.i, this.j);
        for(int m = 0; m < i; m++){
            for(int n = 0; n < j; n++){
                C.elementos[m][n]= this.elementos[m][n]+B.elementos[m][n];
            }
        }
        return C;
    }
    
    //subtrai B do objeto Matriz corrente
    public Matriz menos(Matriz B){
        if((B.i != this.i) || (B.j != this.j))  throw new RuntimeException("Operacao ilegal entre matrizes");
        Matriz C = new Matriz(this.i, this.j);
        for(int m = 0; m < i; m++){
            for(int n = 0; n < j; n++){
                C.elementos[m][n]= this.elementos[m][n]-B.elementos[m][n];
            }
        }
        return C;
    }

    //multiplica o objeto Matriz corrente por uma constante
    public Matriz vezesConst(double k){
        int linhas = this.i;
        int colunas = this.j;
        Matriz C = new Matriz(linhas, colunas);
        for(int m = 0; m < linhas; m++){
            for(int n = 0; n < colunas; n++){
                C.elementos[m][n] = this.elementos[m][n]*k;
            }
        }
        return C;
    }
    
    //altera o valor de um elemento da matriz
    public Matriz alteraValor(int linha, int coluna, double valor){
        elementos[linha][coluna]=valor;
        return this;
    }
    
    public int getRows(){
        return i;
    }
    
    public int getCols(){
        return j;
    }
    
    //método main é usado para testar a classe e os métodos
    public static void main(String[] args) {
        Matriz A = new Matriz(3,3);
        Matriz X = new Matriz(3,3);
        Matriz B = new Matriz(2,3);
        Matriz C = new Matriz(3,2);
        A.ones();
        B.unit();
        C.zeros();
        System.out.println(A.toString()+"\n"+B.toString()+"\n"+C.toString());
        int cont=0;
        for(int i = 0; i < A.i; i++){
            for(int j = 0; j < A.j; j++){
                A.alteraValor(i, j, cont);
                X.alteraValor(j, i, cont);
                cont++;
            }
        }
        System.out.println(A.toString()+"\n"+X.toString());
        Matriz D = new Matriz(A.soma(X));
        System.out.println(D);
        D = A.menos(X);
        Matriz F = new Matriz(D.transposta());
        System.out.println(D); 
        System.out.println(F);
        D = D.ones().vezesConst(2);
        F = D.menos(A.ones());
        System.out.println(D);
        System.out.println(F);
        int flinhas = F.getRows();
        int fcols = F.getCols();
        System.out.println("linhas: "+flinhas+"\ncolunas: "+fcols);
}
}
    

