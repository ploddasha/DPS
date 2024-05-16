WITH flights_info AS (
    SELECT flight_no, aircraft_code, scheduled_departure
    FROM bookings.flights
),
ticket_prices AS (
    SELECT 
        tf.flight_id,
        tf.fare_conditions,
        tf.amount
    FROM 
        bookings.ticket_flights tf
)
SELECT 
    fi.flight_no,
	to_char(fi.scheduled_departure, 'Day') AS day_of_week,
    tp.fare_conditions,
    MAX(tp.amount) AS max_price,
    MIN(tp.amount) AS min_price,
    ROUND(AVG(tp.amount), 2) AS avg_price
FROM 
    flights_info fi
JOIN 
    ticket_prices tp ON fi.flight_no = (SELECT flight_no FROM bookings.flights WHERE flight_id = tp.flight_id LIMIT 1)
GROUP BY 
	fi.flight_no, to_char(fi.scheduled_departure, 'Day'), tp.fare_conditions
ORDER BY 
    fi.flight_no ASC, tp.fare_conditions;
