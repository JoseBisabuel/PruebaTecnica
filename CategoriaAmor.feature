Feature: CategoriaAmor

  @SeleccionarProductos
  Scenario: Agregar 2 productos de la categoria amor
    Given Cargar Pagina Web Floristeria Munco Flor
    Then Cargar la informacion del DOM CategoriaAmor.json
    And Validar si el elemento Inicio se visualiza
    And Tomar Captura de pantalla: PaginaInicio
    And espereme 2 segundos
    And Hacer Click en El elemento ModuloAmor
    And Hacer Click en El elemento AbrirElemento1
    And espereme 2 segundos
    And Hacer Click en El elemento AñadirAlCarritoElemento
    And espereme 2 segundos
    #And Busque el elemento BotonActualizacion
    And Tomar Captura de pantalla: Elemento1Agregado
    And Hacer Click en El elemento SeguirComprando
    And espereme 2 segundos
    And Hacer Click en El elemento AbrirElemento2
    And espereme 2 segundos
    And Hacer Click en El elemento AñadirAlCarritoElemento
    And espereme 2 segundos
    #And Busque el elemento BotonActualizacion
    And Tomar Captura de pantalla: Elemento1y2Agregado

