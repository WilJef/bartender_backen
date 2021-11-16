package com.aldeamo.bartender.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aldeamo.bartender.entity.Arrays;
import com.aldeamo.bartender.repository.BartenderRepository;

@Service
@Transactional
public class BartenderService {

	@Autowired
	BartenderRepository bartenderRepository;
	
	public List<Arrays> listaPila() {
		return bartenderRepository.findAll();
	}

	public Optional<Arrays> getPilaTrabajo(int idPila) {
		return bartenderRepository.findById(idPila);
	}
	
	public boolean existsById(int id){
        return bartenderRepository.existsById(id);
    }

	public int[] procesarPila(int numeroIteraciones, int idPila) {
		try {
			Optional<Arrays> pila = getPilaTrabajo(idPila);
			
			System.out.println("pila " + pila + pila.get());
			System.out.println("pila ispresent " + pila.isPresent());

			if (pila != null && pila.get() != null && numeroIteraciones > 0) {
				return iniciarProceso((Arrays) pila.get(), numeroIteraciones);
			}

		} catch (Exception e) {
			return new int[1];
		}
		return new int[1];
	}

	private int[] iniciarProceso(Arrays arraysBartender, int numeroIteraciones) {
		
		System.out.println("iniciarProceso aray " + arraysBartender.getInputArray());
		System.out.println("iniciarProceso numeroIteraciones " + numeroIteraciones);
		
		int[] arrayA = new int[1];
		int[] arrayB = new int[1];
		int[] arrayAindice = new int[1];
		int[] arrayRespuesta = new int[1];
		int[] arrayPrimos = retornarListaPrimos();
		
		int primoActual = 0;
		
		try {
			if (arraysBartender != null) {
				String[] array = arraysBartender.getInputArray().split(",");
				
				arrayA = Stream.of(array).mapToInt(Integer::parseInt).toArray();

				if (arrayA == null || arrayA.length == 0)
					return new int[1];

				for (int iteracion = 0; iteracion < numeroIteraciones; iteracion++) {
					primoActual = arrayPrimos[iteracion];
					System.out.println("primo " + primoActual);

					System.out.println("iteracion " + iteracion);
					arrayB = null;
					arrayAindice = null;

					if (arrayA == null || arrayA.length == 0)
						break;

					for (int j = arrayA.length - 1; j >= 0; j--) {
						System.out.println("arrayA[j] mod primo " + arrayA[j] + " " + primoActual);
						if (arrayA[j] % primoActual == 0) {
							System.out.println("adiciona a arrayB arrayA[j] " + arrayA[j]);
							if (arrayB == null || arrayB.length == 0) {
								arrayB = new int[] { arrayA[j] };
							} else {
								arrayB = IntStream.concat(IntStream.of(arrayB), IntStream.of(new int[] { arrayA[j] }))
										.toArray();
							}

						} else {
							System.out.println("adiciona a arrayAindice arrayA[j] " + arrayA[j]);
							if (arrayAindice == null || arrayAindice.length == 0) {
								arrayAindice = new int[] { arrayA[j] };

							} else {
								arrayAindice = IntStream
										.concat(IntStream.of(arrayAindice), IntStream.of(new int[] { arrayA[j] }))
										.toArray();
							}

						}

					}
					if (arrayB != null) {
					for (int i = 0; i < arrayB.length; i++) {
						System.out.println("arrayB = " + arrayB[i]);
					}
					}
					if (arrayAindice != null) {
						for (int i = 0; i < arrayAindice.length; i++) {
							System.out.println("arrayAindice = " + arrayAindice[i]);
						}
					}

					arrayA = arrayAindice;

					if (arrayRespuesta == null || arrayRespuesta.length == 0) {
						arrayRespuesta = arrayB;

					} else {
						if (arrayB != null && arrayB.length > 0) {
							arrayRespuesta = IntStream.concat(IntStream.of(arrayRespuesta), IntStream.of(arrayB))
									.toArray();
						}
					}
					for (int i = 0; i < arrayRespuesta.length; i++) {
						System.out.println("arrayRespuesta = " + arrayRespuesta[i]);
					}

				}
				if (arrayRespuesta == null || arrayRespuesta.length == 0) {
					arrayRespuesta = arrayAindice;

				} else {
					if (arrayAindice != null && arrayAindice.length > 0) {
						arrayRespuesta = IntStream.concat(IntStream.of(arrayRespuesta), IntStream.of(arrayAindice))
								.toArray();
					}
				}

				for (int i = 0; i < arrayRespuesta.length; i++) {
					System.out.println("arrayRespuesta = " + arrayRespuesta[i]);
				}
				
				return arrayRespuesta;

			}
		} catch (Exception e) {
			return new int[1];
		}
		
		return new int[1];
	}
	
	private int[] retornarListaPrimos() {
		return new int[]{2, 3, 5, 7, 11, 13};
	}

}
