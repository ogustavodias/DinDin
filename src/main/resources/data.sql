INSERT INTO
  tb_users (name, email, password)
VALUES
  (
    'Teste',
    'teste@hotmail.com',
    '$2a$12$.c8sDoso9IJ.nfLd0bBzWu6uYbx3lbJVq8lDL8nKmugB.QM8LjdEi'
  );

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