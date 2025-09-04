package com.example.juegogato;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.juegogato.databinding.ActivityJuegoAutomaticoBinding;

public class JuegoAutomatico extends AppCompatActivity {

    private ActivityJuegoAutomaticoBinding binding;
    private boolean turno = false;
    private int[][] matrizGato = new int[3][3];
    private int contadorJugadas = 0;
    private boolean hayGanador = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityJuegoAutomaticoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.btnA1.setOnClickListener(view -> {
            mostrarIcono(binding.icA1);
            binding.btnA1.setEnabled(false);
            jugarMaquina();
        });

        binding.btnA2.setOnClickListener(view -> {
            mostrarIcono(binding.icA2);
            binding.btnA2.setEnabled(false);
            jugarMaquina();
        });

        binding.btnA3.setOnClickListener(view -> {
            mostrarIcono(binding.icA3);
            binding.btnA3.setEnabled(false);
            jugarMaquina();
        });

        binding.btnB1.setOnClickListener(view -> {
            mostrarIcono(binding.icB1);
            binding.btnB1.setEnabled(false);
            jugarMaquina();
        });

        binding.btnB2.setOnClickListener(view -> {
            mostrarIcono(binding.icB2);
            binding.btnB2.setEnabled(false);
            jugarMaquina();
        });

        binding.btnB3.setOnClickListener(view -> {
            mostrarIcono(binding.icB3);
            binding.btnB3.setEnabled(false);
            jugarMaquina();
        });

        binding.btnC1.setOnClickListener(view -> {
            mostrarIcono(binding.icC1);
            binding.btnC1.setEnabled(false);
            jugarMaquina();
        });

        binding.btnC2.setOnClickListener(view -> {
            mostrarIcono(binding.icC2);
            binding.btnC2.setEnabled(false);
            jugarMaquina();
        });

        binding.btnC3.setOnClickListener(view -> {
            mostrarIcono(binding.icC3);
            binding.btnC3.setEnabled(false);
            jugarMaquina();
        });

        binding.btnReiniciar.setOnClickListener(view -> {
            reiniciarJuego();
        });
    }

    private void jugarMaquina() {
        if (hayGanador || contadorJugadas == 9) return;
        while (true) {
            int filasRandom = (int) (Math.random() * 3); // 0, 1 o 2
            int columnasRandom = (int) (Math.random() * 3); // 0, 1 o 2
            if (matrizGato[filasRandom][columnasRandom] == 0) {
                ImageView imagen = obtenerImagen(filasRandom, columnasRandom);
                CardView boton = obtenerBoton(filasRandom, columnasRandom);
                // Esperar antes de mostrar la jugada
                imagen.postDelayed(() -> {
                    mostrarIcono(imagen);
                    boton.setEnabled(false);
                }, 200); // 2 milisegundos = 0,2 segundo

                return;
            }
        }
    }

    private void mostrarIcono(ImageView imagen) {

        if (turno) {
            imagen.setImageResource(R.drawable.ic_x);
            ingresarJugada(imagen, turno);
            turno = false;
        } else {
            imagen.setImageResource(R.drawable.ic_circle);
            ingresarJugada(imagen, turno);
            turno = true;
        }
        verificarJugada();
    }

    private void verificarJugada() {
        contadorJugadas += 1;

        // Ganó en filas
        for (int i = 0; i < matrizGato.length; i++) {
            int totalFilas = 0;
            for (int j = 0; j < matrizGato[i].length; j++) {
                totalFilas += matrizGato[i][j];
            }
            verificarGanador(totalFilas);
        }

        // Ganó en columnas
        for (int j = 0; j < matrizGato.length; j++) {
            int totalColumnas = 0;
            for (int i = 0; i < matrizGato[j].length; i++) {
                totalColumnas += matrizGato[i][j];
            }
            verificarGanador(totalColumnas);
        }

        // Ganó en diagonal izqquieda
        if (matrizGato[2][0] != 0 && matrizGato[1][1] != 0 && matrizGato[0][2] != 0) {
            int totalDiagonalIzquirda = matrizGato[2][0] + matrizGato[1][1] + matrizGato[0][2];
            verificarGanador(totalDiagonalIzquirda);
        }

        //Ganó diagonal derecha
        if (matrizGato[0][0] != 0 && matrizGato[1][1] != 0 && matrizGato[2][2] != 0) {
            int totalDiagonalDerecha = matrizGato[0][0] + matrizGato[1][1] + matrizGato[2][2];
            verificarGanador(totalDiagonalDerecha);
        }

        if (!hayGanador && contadorJugadas == 9) {
            Toast.makeText(JuegoAutomatico.this, "Ha sido un empate, reinicie el juego", Toast.LENGTH_LONG).show();
        }
    }

    private void verificarGanador(int total) {

        if (total == 6) {
            hayGanador = true;
            Toast.makeText(JuegoAutomatico.this, "Ganó X: Máquina", Toast.LENGTH_LONG).show();
            desahabilitarBotones();
        } else if (total == 99) {
            hayGanador = true;
            Toast.makeText(JuegoAutomatico.this, "Ganó O: Persona", Toast.LENGTH_LONG).show();
            desahabilitarBotones();
        }

    }

    private void desahabilitarBotones() {
        //Se deshabilitan los botones
        binding.btnA1.setEnabled(false);
        binding.btnA2.setEnabled(false);
        binding.btnA3.setEnabled(false);
        binding.btnB1.setEnabled(false);
        binding.btnB2.setEnabled(false);
        binding.btnB3.setEnabled(false);
        binding.btnC1.setEnabled(false);
        binding.btnC2.setEnabled(false);
        binding.btnC3.setEnabled(false);
    }

    private void ingresarJugada(ImageView imagen, boolean turno) {
        if (imagen == binding.icA1) {
            matrizGato[0][0] = (turno) ? 2 : 33;
        } else if (imagen == binding.icA2) {
            matrizGato[0][1] = (turno) ? 2 : 33;
        } else if (imagen == binding.icA3) {
            matrizGato[0][2] = (turno) ? 2 : 33;
        } else if (imagen == binding.icB1) {
            matrizGato[1][0] = (turno) ? 2 : 33;
        } else if (imagen == binding.icB2) {
            matrizGato[1][1] = (turno) ? 2 : 33;
        } else if (imagen == binding.icB3) {
            matrizGato[1][2] = (turno) ? 2 : 33;
        } else if (imagen == binding.icC1) {
            matrizGato[2][0] = (turno) ? 2 : 33;
        } else if (imagen == binding.icC2) {
            matrizGato[2][1] = (turno) ? 2 : 33;
        } else if (imagen == binding.icC3) {
            matrizGato[2][2] = (turno) ? 2 : 33;
        }
    }

    private CardView obtenerBoton(int filasRandom, int columnasRandom) {
        if (filasRandom == 0 && columnasRandom == 0) {
            return binding.btnA1;
        } else if (filasRandom == 0 && columnasRandom == 1) {
            return binding.btnA2;
        } else if (filasRandom == 0 && columnasRandom == 2) {
            return binding.btnA3;
        } else if (filasRandom == 1 && columnasRandom == 0) {
            return binding.btnB1;
        } else if (filasRandom == 1 && columnasRandom == 1) {
            return binding.btnB2;
        } else if (filasRandom == 1 && columnasRandom == 2) {
            return binding.btnB3;
        } else if (filasRandom == 2 && columnasRandom == 0) {
            return binding.btnC1;
        } else if (filasRandom == 2 && columnasRandom == 1) {
            return binding.btnC2;
        } else if (filasRandom == 2 && columnasRandom == 2) {
            return binding.btnC3;
        } else {
            return null;
        }
    }

    private ImageView obtenerImagen(int fila, int columna) {
        if (fila == 0 && columna == 0) {
            return binding.icA1;
        } else if (fila == 0 && columna == 1) {
            return binding.icA2;
        } else if (fila == 0 && columna == 2) {
            return binding.icA3;
        } else if (fila == 1 && columna == 0) {
            return binding.icB1;
        } else if (fila == 1 && columna == 1) {
            return binding.icB2;
        } else if (fila == 1 && columna == 2) {
            return binding.icB3;
        } else if (fila == 2 && columna == 0) {
            return binding.icC1;
        } else if (fila == 2 && columna == 1) {
            return binding.icC2;
        } else if (fila == 2 && columna == 2) {
            return binding.icC3;
        } else {
            return null;
        }
    }
    private void reiniciarJuego() {

        // Desaparecen los iconos
        binding.icA1.setImageResource(R.drawable.ic_blank);
        binding.icA2.setImageResource(R.drawable.ic_blank);
        binding.icA3.setImageResource(R.drawable.ic_blank);
        binding.icB1.setImageResource(R.drawable.ic_blank);
        binding.icB2.setImageResource(R.drawable.ic_blank);
        binding.icB3.setImageResource(R.drawable.ic_blank);
        binding.icC1.setImageResource(R.drawable.ic_blank);
        binding.icC2.setImageResource(R.drawable.ic_blank);
        binding.icC3.setImageResource(R.drawable.ic_blank);

        //Se habilitan los botones
        binding.btnA1.setEnabled(true);
        binding.btnA2.setEnabled(true);
        binding.btnA3.setEnabled(true);
        binding.btnB1.setEnabled(true);
        binding.btnB2.setEnabled(true);
        binding.btnB3.setEnabled(true);
        binding.btnC1.setEnabled(true);
        binding.btnC2.setEnabled(true);
        binding.btnC3.setEnabled(true);

        //Se inicializa el turno
        turno = false;

        //Reinicio de la matriz
        for (int i = 0; i < matrizGato.length; i++) {
            for (int j = 0; j < matrizGato[i].length; j++) {
                matrizGato[i][j] = 0;
            }
        }

        //Reiniciar el contador de jugadas y ganador
        contadorJugadas = 0;
        hayGanador = false;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }

}