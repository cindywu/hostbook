--name:save-message!
-- creates a new message
INSERT INTO hostbook
(name, message, timestamp)
VALUES (:name, :message, :timestamp)

--name:get-messages
-- selects all available messages
SELECT * from hostbook

