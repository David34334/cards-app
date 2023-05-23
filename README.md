# Bank Inc - Card App

Aplicación desarrollada para el manejo de `tarjetas de crédito o débito`.

## Instrucciones para correr la aplicación:

La aplicación está construida en `Java - Springboot` utilizando `gradle` con `JDK 11`.

El puerto definido en el `application.yml` es el  `8085`.

La base de datos utilizada para este proyecto fué `PostgreSQL`.

## Generalidades:

Código `111000` corresponde a una tarjeta débito.

Código `222000` corresponde a una tarjeta de crédito.

### Cards - Endpoints:

*Generar número de tarjeta (GET):* `http://localhost:8085/api/v1/card/111000/number?user_id=29d722a5-0fc5-400b-82cd-b6890d6b0b56`.

*Consulta de saldo (GET):* `http://localhost:8085/api/v1/card/balance/1110004483001122`.

*Activar tarjeta (POST):* `http://localhost:8085/api/v1/card/enroll`.

Body: 
`
  {
    "card_number": "1110004483001122"
  }
`
