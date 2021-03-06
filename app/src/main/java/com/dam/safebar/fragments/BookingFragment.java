package com.dam.safebar.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.dam.safebar.R;
import com.dam.safebar.javabeans.ReservaRest;
import com.dam.safebar.javabeans.ReservaUsu;
import com.dam.safebar.javabeans.Restaurante;
import com.dam.safebar.javabeans.Usuario;
import com.dam.safebar.listeners.ReservarListener;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Locale;


public class BookingFragment extends Fragment {

    public static final String COD_REST_UID_BOOKING = "RUID";
    public static final String COD_REST_NOM_BOOKING = "RNOM";

    String restUID;
    String restNom;
    String codigoReserva;
    ReservaUsu reservaUsu;
    ReservaRest reservaRest;
    ReservaRest reservaRestCheck;
    Restaurante restaurante;
    Usuario usuario;

    ReservarListener listener;

    FirebaseAuth fba;
    FirebaseUser user;
    DatabaseReference dbRef;
    ValueEventListener vel;

    String fecha;
    String hora;
    String sNumPersonas;
    int numPersonas;
    int contAforo;
    boolean isCena;


    EditText etFecha;
    EditText etHora;
    EditText etNumPersonas;
    Button btnReservar;

    public BookingFragment() {
        // Required empty public constructor
    }

    public BookingFragment newInstance(String restUID, String restNom) {
        BookingFragment fragment = new BookingFragment();
        Bundle args = new Bundle();
        args.putString(COD_REST_UID_BOOKING, restUID);
        args.putString(COD_REST_NOM_BOOKING, restNom);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            restUID = getArguments().getString(COD_REST_UID_BOOKING);
            restNom = getArguments().getString(COD_REST_NOM_BOOKING);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_booking, container, false);

        etFecha = view.findViewById(R.id.etBookingFragFecha);
        etFecha.setEnabled(false);
        etHora = view.findViewById(R.id.etBookingFragHora);
        etHora.setEnabled(false);
        etNumPersonas = view.findViewById(R.id.etBookingFragNumPers);
        btnReservar = view.findViewById(R.id.btnBookingFragReservar);

        SwitchMaterial comidaCena = view.findViewById(R.id.switchComidaCena);

//        reservaOk = false;

        comidaCena.setOnCheckedChangeListener((buttonView, isChecked) -> isCena = isChecked);

        Button btnFecha = view.findViewById(R.id.btnSelecFecha);
        btnFecha.setOnClickListener(v -> {
            CalendarConstraints.Builder calendarConstraints  =
                    new CalendarConstraints.Builder().setValidator(DateValidatorPointForward.now());
            MaterialDatePicker.Builder<?> builder =
                    MaterialDatePicker.Builder.datePicker().setCalendarConstraints(calendarConstraints.build());


            MaterialDatePicker<?> picker = builder.build();

            picker.show(getChildFragmentManager(), picker.toString());
            etFecha.setEnabled(false);

            //En la documentacion se usa el metodo sin parametrizar, el warning no deberia llegar a causar un error.

            picker.addOnPositiveButtonClickListener((MaterialPickerOnPositiveButtonClickListener) selection -> {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.US);
                String formatted = simpleDateFormat.format(selection);
                etFecha.setText(formatted);
                Log.i("BOOKING_FRAGMENT", "FECHA: " + formatted);


            });

            picker.addOnNegativeButtonClickListener(v1 -> Log.i("BOOKING_FRAGMENT", "DISMISS DIALOGO FECHA"));
        });


        Button btnHora = view.findViewById(R.id.btnSelecHora);
        btnHora.setOnClickListener(v -> {

            TimePickerDialog tp;
            if (isCena) {
                tp = TimePickerDialog.newInstance((view12, hourOfDay, minute, second) -> {
                    String minuteAux = String.valueOf(minute);
                    if (minute < 10) {
                        minuteAux = "0" + minute;
                    }
                    String hora = hourOfDay + ":" + minuteAux;
                    etHora.setText(hora);
                }, true);
                tp.setMaxTime(22,0,0);
                tp.setMinTime(20,0,0);

            } else {
                tp = TimePickerDialog.newInstance((view1, hourOfDay, minute, second) -> {
                    String minuteAux = String.valueOf(minute);
                    if (minute < 10) {
                        minuteAux = "0" + minute;
                    }
                    String hora = hourOfDay + ":" + minuteAux;
                    etHora.setText(hora);
                }, true);
                tp.setMaxTime(15,0,0);
                tp.setMinTime(13,0,0);

            }
            tp.enableMinutes(false);
            tp.setTimeInterval(1);
            tp.show(getFragmentManager(), "Datepickerdialog");

//                MaterialTimePicker.Builder builder = new MaterialTimePicker.Builder()
//                        .setHour(13)
//                        .setMinute(0)
//                        .setInputMode(MaterialTimePicker.INPUT_MODE_CLOCK)
//                        .setTitleText("Hora de reserva")
//                        .setTimeFormat(TimeFormat.CLOCK_12H);
//
//                MaterialTimePicker picker = builder.build();
//
//                picker.show(getChildFragmentManager(), picker.toString());
//                etHora.setEnabled(false);
//
//                picker.addOnPositiveButtonClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        picker.getInputMode();
//                        String time = String.valueOf(picker.getHour()) + ":" + String.valueOf(picker.getMinute());
//                        etHora.setText(time);
//                    }
//                });

        });

        contAforo = 0;

        dbRef = FirebaseDatabase.getInstance().getReference("datos");
        fba = FirebaseAuth.getInstance();
        user = fba.getCurrentUser();



        btnReservar.setOnClickListener(v -> {

            fecha = etFecha.getText().toString().trim();
            hora = etHora.getText().toString().trim();
            sNumPersonas = etNumPersonas.getText().toString().trim();

            if (fecha.isEmpty() || hora.isEmpty() || sNumPersonas.isEmpty()) {

                Snackbar snackbar = Snackbar
                        .make(getActivity().getWindow().getDecorView().getRootView(), R.string.faltan_datos, Snackbar.LENGTH_LONG)
                        .setBackgroundTint(getResources().getColor(R.color.orange_dark));
                snackbar.setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE);
                snackbar.setAnchorView(R.id.btnBookingFragReservar);
                snackbar.show();

            } else {
                numPersonas = Integer.parseInt(sNumPersonas);
                codigoReserva = crearCodigoReserva();

                reservaUsu = new ReservaUsu(restNom, fecha, hora, numPersonas);
                reservaRest = new ReservaRest(user.getUid(), fecha, hora,  numPersonas);

                addListener1();
//                    reservaOkInicio();

            }

        });


        return view;
    }

//    private void reservaOkInicio() {
//        if (reservaOk) {
//            listener.booking();
//            Log.i("reservaOk", String.valueOf(reservaOk));
//        }
//    }

    private String crearCodigoReserva() {

        String codigoUIDs = user.getUid().substring(0,3) + restUID.substring(0,3);
        String codigoDatosReserva = fecha.replace("-", "1") +
                hora.replace(":", "1") + numPersonas;

        return codigoUIDs + codigoDatosReserva;

    }


    //    @Override
//    public void onResume() {
//        super.onResume();
//        addListener();
//    }

    private void addListener1() {
        removeListener();
        if (vel == null) {
            vel = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    contAforo = 0;

                    for (DataSnapshot dss : dataSnapshot.getChildren()) {
                        reservaRestCheck = dss.getValue(ReservaRest.class);
                        contAforo = contAforo + reservaRestCheck.getNumPersonas();
                    }

                    contAforo = contAforo + numPersonas;

                    addListener2();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Snackbar snackbar = Snackbar
                            .make(getActivity().getWindow().getDecorView().getRootView(), R.string.error_carga_datos, Snackbar.LENGTH_LONG)
                            .setBackgroundTint(getResources().getColor(R.color.orange_dark));
                    snackbar.setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE);
                    snackbar.setAnchorView(R.id.btnBookingFragReservar);
                    snackbar.show();
                }
            };
            dbRef.child("restaurantes").child(restUID).child("reservas").child(fecha).child(hora).addValueEventListener(vel);
        }


    }

    private void addListener2() {

        removeListener();

        if (vel == null) {
            vel = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    restaurante = dataSnapshot.getValue(Restaurante.class);

                    if (contAforo > restaurante.getAforo()) {

                        Snackbar snackbar = Snackbar
                                .make(getActivity().getWindow().getDecorView().getRootView(), R.string.aforo_completo, Snackbar.LENGTH_LONG)
                                .setBackgroundTint(getResources().getColor(R.color.orange_dark));
                        snackbar.setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE);
                        snackbar.setAnchorView(R.id.btnBookingFragReservar);
                        snackbar.show();

                    } else {

                        addListener3();

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Snackbar snackbar = Snackbar
                            .make(getActivity().getWindow().getDecorView().getRootView(), R.string.error_carga_datos, Snackbar.LENGTH_LONG)
                            .setBackgroundTint(getResources().getColor(R.color.orange_dark));
                    snackbar.setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE);
                    snackbar.setAnchorView(R.id.btnBookingFragReservar);
                    snackbar.show();
                }
            };
            dbRef.child("restaurantes").child(restUID).addValueEventListener(vel);
        }

    }

    private void addListener3() {

        removeListener();

        if (vel == null) {
            vel = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    usuario = dataSnapshot.getValue(Usuario.class);
                    reservaRest.setNomUsu(usuario.getNombre());

                    dbRef.child("usuarios").child(user.getUid()).child("reservas").child(fecha).child(codigoReserva).setValue(reservaUsu);
                    dbRef.child("restaurantes").child(restUID).child("reservas").child(fecha).child(hora).child(codigoReserva).setValue(reservaRest);

                    removeListener();
//                    reservaOk = true;
                    Log.i("LISTENERBOOKING", "OnDataChange FUERA IF");
                    if (getActivity() != null) {
                        if (getActivity().getSupportFragmentManager() != null) {
                            Fragment f = getActivity().getSupportFragmentManager().findFragmentById(R.id.flRestaurante);
                            if (f instanceof BookingFragment) {
                                Log.i("LISTENERBOOKING", "OnDataChange + " + getActivity());
                                listener.booking();
                            }
                        }

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Snackbar snackbar = Snackbar
                            .make(getActivity().getWindow().getDecorView().getRootView(), R.string.error_carga_datos, Snackbar.LENGTH_LONG)
                            .setBackgroundTint(getResources().getColor(R.color.orange_dark));
                    snackbar.setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE);
                    snackbar.setAnchorView(R.id.btnBookingFragReservar);
                    snackbar.show();
                }
            };
            dbRef.child("usuarios").child(user.getUid()).addValueEventListener(vel);
        }

    }




    @Override
    public void onPause() {
        super.onPause();
        removeListener();
    }

    private void removeListener() {
        if (vel != null) {
            dbRef.removeEventListener(vel);
            vel = null;
        }

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ReservarListener) {
            listener = (ReservarListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

}