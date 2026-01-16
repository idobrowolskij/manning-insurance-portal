-- Enable UUID extension
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- =========================
-- Countries
-- =========================
CREATE TABLE countries (
    country_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(64) NOT NULL,
    population INT
);

-- =========================
-- States
-- =========================
CREATE TABLE states (
    state_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    country_id UUID NOT NULL,
    name VARCHAR(64) NOT NULL,
    population INT,

    CONSTRAINT fk_states_country
        FOREIGN KEY (country_id)
        REFERENCES countries(country_id)
);

-- =========================
-- Cities
-- =========================
CREATE TABLE cities (
    city_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    country_id UUID NOT NULL,
    state_id UUID,
    name VARCHAR(64) NOT NULL,
    population INT,

    CONSTRAINT fk_cities_country
        FOREIGN KEY (country_id)
        REFERENCES countries(country_id),

    CONSTRAINT fk_cities_state
        FOREIGN KEY (state_id)
        REFERENCES states(state_id)
);

-- =========================
-- Addresses
-- =========================
CREATE TABLE addresses (
    address_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    country_id UUID NOT NULL,
    city_id UUID NOT NULL,
    state_id UUID,
    address VARCHAR(128) NOT NULL,
    postal_code VARCHAR(16),

    CONSTRAINT fk_addresses_country
        FOREIGN KEY (country_id)
        REFERENCES countries(country_id),

    CONSTRAINT fk_addresses_city
        FOREIGN KEY (city_id)
        REFERENCES cities(city_id),

    CONSTRAINT fk_addresses_state
        FOREIGN KEY (state_id)
        REFERENCES states(state_id)
);

-- =========================
-- Customers
-- =========================
CREATE TABLE customers (
    customer_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    address_id UUID,
    name VARCHAR(64) NOT NULL,
    surname VARCHAR(64) NOT NULL,
    email VARCHAR(320) NOT NULL,
    created TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),

    CONSTRAINT fk_customers_address
        FOREIGN KEY (address_id)
        REFERENCES addresses(address_id),

    CONSTRAINT uq_customers_email UNIQUE (email)
);

-- =========================
-- Policies
-- =========================
CREATE TABLE policies (
    policy_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(16) NOT NULL,
    description TEXT,
    price NUMERIC(4,2) NOT NULL,
    created TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now()
);

-- =========================
-- Coverages
-- =========================
CREATE TABLE coverages (
    coverage_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(16) NOT NULL,
    description TEXT
);

-- =========================
-- Policies_Coverages (M:N)
-- =========================
CREATE TABLE policies_coverages (
    policy_id UUID NOT NULL,
    coverage_id UUID NOT NULL,

    PRIMARY KEY (policy_id, coverage_id),

    CONSTRAINT fk_pc_policy
        FOREIGN KEY (policy_id)
        REFERENCES policies(policy_id),

    CONSTRAINT fk_pc_coverage
        FOREIGN KEY (coverage_id)
        REFERENCES coverages(coverage_id)
);

-- =========================
-- Subscriptions
-- =========================
CREATE TABLE subscriptions (
    policy_id UUID NOT NULL,
    customer_id UUID NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    paid_price NUMERIC(4,2) NOT NULL,
    created TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),

    PRIMARY KEY (policy_id, customer_id),

    CONSTRAINT fk_subscriptions_policy
        FOREIGN KEY (policy_id)
        REFERENCES policies(policy_id),

    CONSTRAINT fk_subscriptions_customer
        FOREIGN KEY (customer_id)
        REFERENCES customers(customer_id)
);