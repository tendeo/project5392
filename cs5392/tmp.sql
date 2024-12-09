-- Inbox
INSERT IGNORE INTO `Inbox` (InboxID, MessageID, TimeReceived, OwnerNodeID, InboxSize) VALUES
('Inbox', 'N2M8', NOW(), 'N1', 10),
('Inbox', 'N8M11', NOW(), 'N1', 10),
('Inbox', 'N12M2', NOW(), 'N1', 10),
('Inbox', 'N5M12', NOW(), 'N1', 10),
('Inbox2', 'N1M2', NOW(), 'N2', 20),
('Inbox2', 'N12M3', NOW(), 'N2', 20),
('Inbox2', 'N8M2', NOW(), 'N2', 20),
('Inbox2', 'N10M4', NOW(), 'N2', 20),
('Inbox10', 'N1M1', NOW(), 'N10', 30),
('Inbox10', 'N12M4', NOW(), 'N10', 30),
('Inbox10', 'N7M7', NOW(), 'N10', 30),
('Inbox10', 'N8M3', NOW(), 'N10', 30),
('Inbox10', 'N1M4', NOW(), 'N10', 30);
