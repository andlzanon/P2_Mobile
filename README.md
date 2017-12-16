# P2_Mobile
  
  Segunda avaliação da disciplina de Tópicos em Informática 12 - Desenvolvimento Mobile.
  
# Apresentação
  
	Aplicativo Android que acessa dados de acões sociais da web por meio de um arquivo JSON e exibê-os em uma RecyclerView. 
	Ao clicar em um item na tela de detalhes é aberta uma tela de descrição com um texto sobre o que é feito e um botão que leva ao site do item clicado.
  	Sempre que o aplicativo entra na internet todos os dados são salvos em um bando de dados SQLite. 
  	Os dados do BD só são acessados se o usuário está sem internet. 
  	Se entrar novamente, o banco de dados é inteiramente deletado, os dados sao importados da internet via JSON e são salvos novamente no BD.

	O projeto foi implementado no padrão de desenvolvimento Model View Presenter.

## Referências a bibliotecas externas
 
* [ButterKnife](https://github.com/JakeWharton/butterknife)

* [CircleImageView](https://github.com/hdodenhof/CircleImageView)

* [Retrofit](https://github.com/square/retrofit)

* [GSON](https://github.com/google/gson)

* [Picasso](https://github.com/square/picasso)

## Aluno
* André Levi Zanon

## Professores 
* Bruno De Azevedo Mendonça
* César Teixeira
