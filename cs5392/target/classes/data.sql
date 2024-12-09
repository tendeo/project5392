-- Roles
INSERT IGNORE INTO Role (name) VALUES ('ROLE_ADMIN');
INSERT IGNORE INTO Role (name) VALUES ('ROLE_OPERATOR');

-- Users
INSERT IGNORE INTO User (UserName, Password, FirstName, LastName, Email, Type)
VALUES ('admin', '$2a$12$92FDVLPePGPRb8eYaIQ3X.Q9xorjea4Mx/NPDdXxRwc.Blthjh3HG', 'Admin', 'User', 'admin@example.com', 'Admin'); -- Password: admin123

INSERT IGNORE INTO User (UserName, Password, FirstName, LastName, Email, Type)
VALUES ('operator', '$2a$12$mTG2siQ//XfkQ6d33utth.G/MYl33y.1wlF8Sqnmi9orUqQ8qQlqS', 'Operator', 'User', 'operator@example.com', 'Operator'); -- Password: operator123

-- UserRoles
INSERT IGNORE INTO UserRoles (user_id, role_id) VALUES (1, 1); -- Admin user
INSERT IGNORE INTO UserRoles (user_id, role_id) VALUES (2, 2); -- Operator user

-- Nodes
INSERT IGNORE INTO `Node` (`NodeID`, `NetworkID`, `LeftNeighborID`, `RightNeighborID`, `InboxID`, `StoreID`, `Status`)
VALUES
('N1', 'Network1', 'N10', 'N2', 'Inbox1', 'Store1', 1),
('N2', 'Network1', 'N1', 'N4', 'Inbox2', 'Store2', 1),
('N4', 'Network1', 'N2', 'N5', 'Inbox3', 'Store3', 1),
('N5', 'Network1', 'N4', 'N12', 'Inbox4', 'Store4', 1),
('N12', 'Network1', 'N5', 'N7', 'Inbox5', 'Store5', 1),
('N7', 'Network1', 'N12', 'N8', 'Inbox6', 'Store6', 1),
('N8', 'Network1', 'N7', 'N17', 'Inbox7', 'Store7', 1),
('N17', 'Network1', 'N8', 'N10', 'Inbox8', 'Store8', 1),
('N10', 'Network1', 'N17', 'N1', 'Inbox9', 'Store9', 1);

-- Messages

INSERT IGNORE INTO Message (MessageID, SenderID, ReceiverID, TimeStampCreated, TimeStampReceived, Direction, Path, Status, Message) VALUES
('N2M8', 'N2', 'N1', NOW(), NOW(), 'Left', 'N2->N1', 'Delivered', 'Would you like to join us for a group study?'),
('N8M11', 'N8', 'N1', NOW(), NOW(), 'Right', 'N8->N17->N10->N1', 'Delivered', 'There is a tutorial session today'),
('N12M2', 'N12', 'N1', NOW(), NOW(), 'Left', 'N12->N5->N4->N2->N1', 'Delivered', 'Can I join your group for discussion?'),
('N5M12', 'N5', 'N1', NOW(), NOW(), 'Left', 'N5->N4->N2->N1', 'Delivered', 'I did not see N3 in the ring. Is it active or was it removed?'),
('N2M2', 'N2', 'N1', NOW(), NOW(), 'Left', 'N2->N1', 'Delivered', 'Hi I am your neighbor'),
('N10M1', 'N10', 'N1', NOW(), NOW(), 'Right', 'N10->N1', 'Delivered', 'I am your neighbor and just joined'),
('N12M1', 'N12', 'N1', NOW(), NOW(), 'Right', 'N12->N7->N8->N17->N10-N1', 'Delivered', 'Hi I am a new member joining the ring'),
('N1M2', 'N1', 'N2', NOW(), NOW(), 'Right', 'N1->N2', 'Delivered', 'Acknowledgment: Hi neighbor'),
('N12M3', 'N12', 'N2', NOW(), NOW(), 'Left', 'N12->N5->N4->N2', 'Delivered', 'Can I join your group for discussion?'),
('N8M2', 'N8', 'N2', NOW(), NOW(), 'Left', 'N8->N7->N12->N5->N4-N2', 'Delivered', 'There is a tutorial session today'),
('N10M4', 'N10', 'N2', NOW(), NOW(), 'Right', 'N10->N1->N2', 'Delivered', 'I just let you know that your neighbor N3 has been removed'),
('N2M1', 'N2', 'N2', NOW(), NOW(), 'Left', 'N2->N2', 'Delivered', 'Testing self message'),
('N4M6', 'N4', 'N2', NOW(), NOW(), 'Left', 'N4->N2', 'Delivered', 'Leaving today'),
('N5M4', 'N5', 'N2', NOW(), NOW(), 'Left', 'N5->N4->N2', 'Delivered', 'I do not come today because N4 is leaving today'),
('N10M2', 'N10', 'N2', NOW(), NOW(), 'Right', 'N10->N1->N2', 'Delivered', 'I would like to join your group'),
('N2M4', 'N2', 'N4', NOW(), NOW(), 'Right', 'N2->N4', 'Delivered', 'Are you still available?'),
('N2M5', 'N2', 'N4', NOW(), NOW(), 'Right', 'N2->N4', 'Delivered', 'If you are still in town, can you bring your laptop?'),
('N12M3', 'N12', 'N4', NOW(), NOW(), 'Left', 'N12->N5->N4', 'Delivered', 'I would like to join your group'),
('N1M1', 'N1', 'N10', NOW(), NOW(), 'Left', 'N1->N10', 'Delivered', 'I am your new neighbor'),
('N12M4', 'N12', 'N10', NOW(), NOW(), 'Right', 'N12->N7->N8->N17->N10', 'Delivered', 'Hi I am a new member joining the ring'),
('N7M7', 'N7', 'N10', NOW(), NOW(), 'Right', 'N7->N8->N10', 'Delivered', 'There was a connection problem with N8 before'),
('N8M3', 'N8', 'N10', NOW(), NOW(), 'Right', 'N8->N10', 'Delivered', 'I have a problem connecting with other nodes'),
('N1M4', 'N1', 'N10', NOW(), NOW(), 'Right', 'N1->N2->N4->N5->N12->N7->N8->N17->N10', 'Delivered', 'Testing the full ring connection; please acknowledge'),
('N1M3', 'N1', 'N10', NOW(), NOW(), 'Left', 'N1->N10', 'Delivered', 'Testing neighborhood connection'),
('N10M5', 'N10', 'N10', NOW(), NOW(), 'Left', 'N10->N10', 'Delivered', 'Testing self message'),
('N7M3', 'N7', 'N10', NOW(), NOW(), 'Right', 'N7->N8->N10', 'Delivered', 'Are you the administrator of the network?'),
('N7M4', 'N7', 'N10', NOW(), NOW(), 'Right', 'N7->N8->N10', 'Delivered', 'I want to check the network status'),
('N1M9', 'N1', 'N5', NOW(), NOW(), 'Right', 'N1->N2->N3->N4->N5', 'Delivered', 'I will introduce you to N3'),
('N1M7', 'N1', 'N5', NOW(), NOW(), 'Right', 'N1->N2->N4->N5', 'Delivered', 'Sorry, N3 is removed from the network');


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

-- Store
INSERT IGNORE INTO `Store` (StoreID, MessageID, TimeReceived, OwnerNodeID) VALUES
('Store', 'N2M2', NOW(), 'N1'),
('Store', 'N10M1', NOW(), 'N1'),
('Store', 'N12M1', NOW(), 'N1'),
('Store2', 'N2M1', NOW(), 'N2'),
('Store2', 'N4M6', NOW(), 'N2'),
('Store2', 'N5M4', NOW(), 'N2'),
('Store2', 'N10M2', NOW(), 'N2'),
('Store4', 'N2M4', NOW(), 'N4'),
('Store4', 'N2M5', NOW(), 'N4'),
('Store4', 'N12M3', NOW(), 'N4'),
('Store10', 'N1M3', NOW(), 'N10'),
('Store10', 'N10M5', NOW(), 'N10'),
('Store10', 'N7M3', NOW(), 'N10'),
('Store10', 'N7M4', NOW(), 'N10'),
('Store5', 'N1M9', NOW(), 'N5'),
('Store5', 'N1M7', NOW(), 'N5');