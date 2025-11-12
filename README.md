# 🤔 Responde las siguientes preguntas:
¿Cuáles son las principales clases de la API de Google Maps para Android y qué función cumple cada una?

-GoogleMap: Es la clase que se encarga de representar el mapa.
-SupportMapFragment y MapView: Son los  componentes que se encargan de actuar como contonedores de un objeto GoogleMap.
-OnMapReadyCallback: Es la clase que se encarga de notificar cuando el mapa se ha cargado u esta listo para usarse.
-LatLng: Representa un punto geográfico en la Tierra usando coordenadas.
-Marker y MarkerOptions: Permiten añadir marcadores y configurar sus propiedades.
-CameraUpdate y CameraUpdateFactory: Permiten controlar la vista del mapa.

¿Cómo se puede agregar un mapa a una aplicación Android usando la API de Google Maps?

1. Obtener una Key de la API de Google.
2. Añadir la dependencia de la API al Gradle.
3. Añadir los permisos necesarios al manifest.
4. Añadir la Key de la API al Manifest.
5. Añadir el mapa al Layout.
6. Inicializar el mapa en el código.
7. Implementar el método onMapReady() para recibir el mapa y usarlo.

¿Qué opciones ofrece la API de Google Maps para mostrar la ubicación del usuario en un mapa?

-Usando "Mi Ubicación".
-Usando "FusedLocationProviderClient" para obtener las coordenadas exactas del usuario.

# ✍️ Reflexión personal del tema

El uso de mapas en aplicaciones móviles representa una gran ventaja para ciertas apps y resulta imprescindible y fundamental para otras,
la cantidad de servicios que se puden ofrecer con este tipo de tecnologías son numerosos. Es por esto que el aprender a usarlos en
en entornos android resulta de lo más interesante y ventajoso, Google nos facilita su API para mapas lo cual hace más sencillo el
implementarlos. Considero que este tema, si bien no es algo fundamental, si es algo que otorga a los desarrolladores un abanico más
amplio de posibilidades y por lo tanto, mayor competitividad.
