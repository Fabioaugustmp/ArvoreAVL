package br.arvore;

import java.util.Scanner;

public class ArvoreAvl {

	private static class Arvore {
		public int numero, alturaD, alturaE;
		public Arvore direita, esquerda; 
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		Arvore raiz = null;
		Arvore aux;
		int opcao, achou = 0, numero; 
		
		do {
			System.out.println("\n Menu Opções \n");
			System.out.println("1 - Inserir árvore");
			System.out.println("2 - Consultar nó da árvore");
			System.out.println("3 - Consultar toda árvore em ordem");
			System.out.println("4 - Consultar toda árvore em pré-ordem");
			System.out.println("5 - Consultar toda a árvore em pós-ordem");
			System.out.println("6 - Excluir um nó na árvore");
			System.out.println("7 - Esvaziar árvore");
			System.out.println("8 - Sair");
			System.out.println("\n Informe a Opção \n");
			
			opcao = sc.nextInt();

			if (opcao < 1 || opcao > 8) {
				System.out.println("Opção Inválida !");
			}
			
			if (opcao == 1) {
				System.out.println("Informe o valor a ser inserido na árvore: ");
				numero = sc.nextInt();
				raiz = inserir(raiz, numero);
				System.out.println("Número inderido na árvore!");
			}
			
			if (opcao == 2) {
				if (raiz == null) {
					System.out.println("Árvore vazia !");
				}
				else {
					System.out.println("Digite o elemento a ser consultado: ");
					numero = sc.nextInt();
					achou = 0;
					achou = consultar(raiz,numero,achou);
				}
				if (achou == 0) {
					System.out.println("Número não encontrado na árvore!");
				}
				else {
					System.out.println("Número encontrado na árvore!");
				}
			}
			
			if (opcao == 3) {
				if (raiz == null) {
					System.out.println("Árvore vazia!");
				}
				else {
					 System.out.println("Listando todos os elementos da árvore em ordem!");
					 mostraremordem(raiz);
				}
			}
			if(opcao == 4) {
				if (raiz == null) {
					System.out.println("Árovre vazia");
				}
				else {
					System.out.println("Listando todos os elementos da árvore em pré-ordem");
					mostrarpreordem(raiz);
				}
			}
			if (opcao == 5) {
				if(raiz == null) {
					System.out.println("Árvore vazia!");
				}
				else {
					System.out.println("Listando todos os elementos da Árvore pós-ordem");
					
					aux = raiz; 
					mostrarposordem(aux);
				}
			}
			if (opcao == 6) {
				if (raiz == null) {
					System.out.println("Árvore vazia!");
				}
				else {
					System.out.println("Informe o número que deseja excluir: ");
					numero = sc.nextInt();
					achou = 0;
					achou = consultar(raiz, numero, achou);
						if(achou == 0) {
							System.out.println("Número não encontrado na Árvore!");
						}
						else {
							raiz = remover(raiz, numero);
							raiz = atualiza(raiz);
							System.out.println("Número excluido da Árvore!");
						}
				}
			}
			if (opcao == 7) {
				if (raiz == null) {
					System.out.println("Árvore vazia!");
				}
				else {
					raiz = null;
					System.out.println("Árvore esvaziada !");
					}
				}
		} while (opcao != 8);
		
		sc.close();
	}

	public static Arvore inserir(Arvore aux, int numero) {
		Arvore novo;
		if (aux == null) {
				novo = new Arvore ();
				novo.numero = numero;
				novo.alturaD = 0;
				novo.alturaE = 0;
				novo.esquerda = null;
				novo.direita = null; 
				aux = novo;
		}
		
		else if (numero < aux.numero) {
			aux.esquerda = inserir (aux.esquerda, numero);
				if (aux.esquerda.alturaD > aux.esquerda.alturaD) {
					aux.alturaE = aux.esquerda.alturaD + 1;
				}
				else {
					aux.alturaE = aux.esquerda.alturaE + 1;
					aux = balanceamento (aux);
				}
		}
		else {
			aux.direita = inserir (aux.direita, numero);
				if (aux.direita.alturaD > aux.direita.alturaE) {
					aux.alturaD = aux.direita.alturaD + 1;
				}
				else {
					aux.alturaD = aux.direita.alturaE + 1;  
				}
		}
		return aux; 
	}
	
	public static Arvore balanceamento (Arvore aux) {
		int d, df;
		d = aux.alturaD - aux.alturaE;
		if(d == 2) {
			df = aux.direita.alturaE;
				if (df <= 0) {
					aux = rotacao_esquerda (aux);
				}
				else {
					aux.direita = rotacao_direita(aux.direita);
					aux = rotacao_esquerda (aux);
				}
		}
		
		else if (d == -2) {
			df = aux.esquerda.alturaD - aux.esquerda.alturaE;
			
			if (df <= 0) {
				aux = rotacao_direita (aux);
			}
			else {
				aux.esquerda = rotacao_esquerda (aux.esquerda);
				aux = rotacao_direita(aux);
			}
		}
		return aux; 
	}
	
	public static Arvore rotacao_esquerda (Arvore aux) {
		Arvore aux1, aux2;
		aux1 = aux.direita;
		aux2 = aux1.esquerda;
		aux.direita = aux2;
		aux1.esquerda = aux;
		
		if (aux.direita == null) {
			aux.alturaD = 0;
		}
		else if (aux.direita.alturaE > aux.direita.alturaD) {
			aux.alturaD = aux.direita.alturaE  + 1;
		}
			else {
				aux.alturaD = aux.direita.alturaD + 1;
			}
		
		if (aux1.esquerda.alturaD > aux1.esquerda.alturaD) {
			aux1.alturaE = aux1.esquerda.alturaE + 1;
		}
		else {
			aux1.alturaE = aux1.esquerda.alturaD + 1;
		}
		return aux1;
	}
	
	public static Arvore rotacao_direita (Arvore aux) {
		Arvore aux1, aux2;
		aux1 = aux.esquerda;
		aux2 = aux.direita; 
		aux.esquerda = aux2;
		aux1.direita = aux;
		
		if (aux.esquerda == null) {
			aux.alturaE = 0;
			}
		else if (aux.esquerda.alturaE > aux.esquerda.alturaD) { 
			aux.alturaE = aux.esquerda.alturaE + 1;
		}
			else { 	
				aux.alturaE = aux.esquerda.alturaD + 1;
			}
		if(aux.direita.alturaE > aux.direita.alturaD) {
			aux1.alturaD = aux1.direita.alturaE + 1;
		}
		else {
			aux.alturaD = aux1.direita.alturaD + 1;
		}
		return aux1;
	}
	
	public static int consultar (Arvore aux, int numero, int achou) {
		if (aux != null && achou == 0) {
			if (aux.numero == numero) {
				achou = 1;
			}
			else if (numero < aux.numero) {
				achou = consultar (aux.esquerda, numero, achou);
			}
				else {
					achou = consultar (aux.direita, numero, achou);
				}
		}
		return achou;
	}
	
	public static void mostraremordem (Arvore aux) {
		if(aux != null) {
			mostraremordem (aux.esquerda);
			System.out.println(aux.numero + " ");
			mostraremordem(aux.direita);
		}
	}
	
	
	public static void mostrarpreordem (Arvore aux) {
		if (aux != null) {
			System.out.println(aux.numero + " ");
			mostrarpreordem(aux.esquerda);
			mostrarpreordem(aux.direita);
		}
	}
	
	public static void mostrarposordem (Arvore aux) {
		if (aux != null) {
			mostrarposordem(aux.esquerda);
			mostrarposordem(aux.direita);
			System.out.println(aux.numero + " ");
		}
	}
	
	public static Arvore remover (Arvore aux, int numero) {
		Arvore p, p2; 
		if (aux.numero == numero) {
			if (aux.esquerda == aux.direita) {
				return null; 
			}
			else if (aux.esquerda == null) {
				return aux.direita;
			}
			else if (aux.direita == null) {
				return aux.esquerda;
			}
			else {
				p2 = aux.direita;
				p = aux.direita;
				
				while (p.esquerda != null) {
					p = p.esquerda;
				}
				
			p.esquerda = aux.esquerda;
			return p2; 
			}
			
		}
		else if (aux.numero < numero) {
			aux.direita = remover (aux.direita, numero);
		}
		else {
			aux.esquerda = remover (aux.esquerda, numero);
		}
		return aux;
	}
	
	public static Arvore atualiza (Arvore aux) {
		if (aux != null) {
			aux.esquerda = atualiza(aux.esquerda);
			
				if (aux.esquerda == null) {
					aux.alturaE = 0;
				}
				else if (aux.esquerda.alturaE > aux.esquerda.alturaD) {
					aux.alturaE = aux.esquerda.alturaE + 1;
				}
				else {
					aux.alturaE = aux.esquerda.alturaD + 1;
				}
		
				aux.direita = atualiza (aux.direita);
				
				if (aux.direita == null) {
					aux.alturaD = 0;
				}
				else if (aux.direita.alturaE > aux.direita.alturaD) {
					aux.alturaD = aux.direita.alturaE + 1;
				}
				else {
					aux.alturaD = aux.direita.alturaD + 1;
				}
				
				aux = balanceamento (aux);
		}
		return aux;
	}
}
