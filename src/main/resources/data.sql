INSERT INTO
  tb_users (name, email, password)
VALUES
  ('Gustavo', 'gustavo@hotmail.com', 'admin');

INSERT INTO
  tb_users (name, email, password)
VALUES
  ('Adriana', 'adriana@hotmail.com', 'admin');

INSERT INTO
  tb_expenses (
    amount,
    category,
    expense_date,
    user_id,
    description
  )
VALUES
  (100, 1, '2025-03-24', 1, 'Teste');

INSERT INTO
  tb_expenses (
    amount,
    category,
    expense_date,
    user_id,
    description
  )
VALUES
  (200, 3, '2025-03-23', 1, 'Teste 2');

INSERT INTO
  tb_expenses (
    amount,
    category,
    expense_date,
    user_id,
    description
  )
VALUES
  (200, 1, '2025-03-23', 2, 'Teste 3');