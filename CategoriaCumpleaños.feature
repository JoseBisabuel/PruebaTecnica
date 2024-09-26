Feature: CategoriaCumpleaños

  @SeleccionarYEliminarProductos
  Scenario: Agregar y Eliminar un producto de la categoria cumpleaños
    Given Cargar Pagina Web Floristeria Munco Flor
    Then Cargar la informacion del DOM CategoriaCumpleaños.json
    And Validar si el elemento Inicio se visualiza
    And Tomar Captura de pantalla: PaginaInicio
    And espereme 2 segundos
    And Hacer Click en El elemento ModuloCumpleaños
    And espereme 2 segundos
#Se interactúa con el filtro para poder validar que se puede seleccionar articulo que se encuentre en otra de las páginas
    And Seleccione el Texto Ordenar por precio: bajo a alto del Selector Filtrar
    And espereme 2 segundos
    And Hacer Click en El elemento MDF00023
    And espereme 2 segundos
    And Hacer Click en El elemento AñadirAlCarritoElemento
    And espereme 2 segundos
    And Tomar Captura de pantalla: Elemento1Agregado
    And Hacer Click en El elemento EliminarProductoMDF00023
    And espereme 2 segundos
    And Tomar Captura de pantalla: ElementoEliminado

