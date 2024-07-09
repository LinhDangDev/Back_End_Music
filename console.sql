
-- Bảng User
INSERT INTO user (user_id, username, password, email, avatar, create_date, count_sign_in, active, subscription) VALUES
(1,'user1', '', 'user1@example.com', 'avatar1.jpg', '2023-10-27 10:00:00', 5, true, '2024-10-27 10:00:00'),
(2,'user2', '12345678', 'user2@example.com', 'avatar2.jpg', '2023-10-28 12:30:00', 10, true, NULL),
(3,'user3', '12345678', 'user3@example.com', 'avatar3.jpg', '2023-10-29 15:45:00', 15, false, '2023-11-29 15:45:00'),
(4,'user4', '12345678', 'user4@example.com', 'avatar4.jpg', '2023-10-30 18:00:00', 20, true, '2024-10-30 18:00:00'),
(5,'user5', '12345678', 'user5@example.com', 'avatar5.jpg', '2023-10-31 20:30:00', 25, false, NULL),
(6,'user6', '12345678', 'user6@example.com', 'avatar6.jpg', '2023-11-01 22:45:00', 30, true, '2024-11-01 22:45:00'),
(7,'user7', '12345678', 'user7@example.com', 'avatar7.jpg', '2023-11-02 10:00:00', 35, true, NULL),
(8,'user8', '12345678', 'user8@example.com', 'avatar8.jpg', '2023-11-03 12:30:00', 40, false, '2023-12-03 12:30:00'),
(9,'user9', '12345678', 'user9@example.com', 'avatar9.jpg', '2023-11-04 15:45:00', 45, true, '2024-11-04 15:45:00'),
(10,'user10', '12345678', 'user10@example.com', 'avatar10.jpg', '2023-11-05 18:00:00', 50, true, NULL);

-- Bảng Role
INSERT INTO role (name, description) VALUES
 ('USER', 'Người dùng thông thường'),
 ('MODERATOR', 'Người kiểm duyệt'),
 ('EDITOR', 'Người biên tập'),
 ('CONTRIBUTOR', 'Người đóng góp'),
 ('AUTHOR', 'Tác giả'),
 ('SUBSCRIBER', 'Người đăng ký'),
 ('VIP', 'Thành viên VIP'),
 ('GUEST', 'Khách'),
 ('TESTER', 'Người kiểm thử');

-- Bảng Permission
INSERT INTO permission (name, description) VALUES
('READ', 'Quyền đọc dữ liệu'),
('WRITE', 'Quyền ghi dữ liệu'),
('DELETE', 'Quyền xóa dữ liệu'),
('CREATE', 'Quyền tạo dữ liệu'),
('UPDATE', 'Quyền cập nhật dữ liệu'),
('MANAGE', 'Quyền quản lý'),
('APPROVE', 'Quyền phê duyệt'),
('PUBLISH', 'Quyền xuất bản'),
('COMMENT', 'Quyền bình luận'),
('RATE', 'Quyền đánh giá');

-- Bảng User_Roles
INSERT INTO user_roles (user_id, role_name) VALUES
((SELECT user.user_id FROM User WHERE username = 'user1'), 'USER'),
((SELECT user.user_id FROM User WHERE username = 'user2'), 'ADMIN'),
((SELECT user.user_id FROM User WHERE username = 'user3'), 'USER'),
((SELECT user.user_id FROM User WHERE username = 'user4'), 'MODERATOR'),
((SELECT user.user_id FROM User WHERE username = 'user5'), 'USER'),
((SELECT user.user_id FROM User WHERE username = 'user6'), 'ADMIN'),
((SELECT user.user_id FROM User WHERE username = 'user7'), 'USER'),
((SELECT user.user_id FROM User WHERE username = 'user8'), 'EDITOR'),
((SELECT user.user_id FROM User WHERE username = 'user9'), 'USER'),
((SELECT user.user_id FROM User WHERE username = 'user10'), 'ADMIN');

-- Bảng Genre
INSERT INTO genre (genre_name, description) VALUES
('Pop', 'Nhạc Pop'),
('Rock', 'Nhạc Rock'),
('Electronic', 'Nhạc điện tử'),
('Hip-hop', 'Nhạc Hip-hop'),
('R&B', 'Nhạc R&B'),
('Country', 'Nhạc đồng quê'),
('Jazz', 'Nhạc Jazz'),
('Classical', 'Nhạc cổ điển'),
('Blues', 'Nhạc Blues'),
('Folk', 'Nhạc dân gian');

-- Bảng Artist
INSERT INTO artist (artist_name, avatar) VALUES
('Taylor Swift', 'taylor.jpg'),
('Imagine Dragons', 'imaginedragons.jpg'),
('The Chainsmokers', 'thechainsmokers.jpg'),
('Ed Sheeran', 'edsheeran.jpg'),
('Adele', 'adele.jpg'),
('Maroon 5', 'maroon5.jpg'),
('Coldplay', 'coldplay.jpg'),
('Ariana Grande', 'arianagrande.jpg'),
('Drake', 'drake.jpg'),
('Justin Bieber', 'justinbieber.jpg');

-- Bảng Album
INSERT INTO album (album_name, description, release_date, image, artist_id) VALUES
('Lover', 'Album Lover của Taylor Swift', '2019-08-23T00:00:00', 'lover.jpg', (SELECT artist_id FROM Artist WHERE artist_name = 'Taylor Swift')),
('Evolve', 'Album Evolve của Imagine Dragons', '2017-06-23T00:00:00', 'evolve.jpg', (SELECT artist_id FROM Artist WHERE artist_name = 'Imagine Dragons')),
('Sick Boy', 'Album Sick Boy của The Chainsmokers', '2018-01-17T00:00:00', 'sickboy.jpg', (SELECT artist_id FROM Artist WHERE artist_name = 'The Chainsmokers')),
('Divide', 'Album Divide của Ed Sheeran', '2017-03-03T00:00:00', 'divide.jpg', (SELECT artist_id FROM Artist WHERE artist_name = 'Ed Sheeran')),
('25', 'Album 25 của Adele', '2015-11-20T00:00:00', '25.jpg', (SELECT artist_id FROM Artist WHERE artist_name = 'Adele')),
('Red Pill Blues', 'Album Red Pill Blues của Maroon 5', '2017-11-03T00:00:00', 'redpillblues.jpg', (SELECT artist_id FROM Artist WHERE artist_name = 'Maroon 5')),
('A Head Full of Dreams', 'Album A Head Full of Dreams của Coldplay', '2015-12-04T00:00:00', 'aheadfullofdreams.jpg', (SELECT artist_id FROM Artist WHERE artist_name = 'Coldplay')),
('Sweetener', 'Album Sweetener của Ariana Grande', '2018-08-17T00:00:00', 'sweetener.jpg', (SELECT artist_id FROM Artist WHERE artist_name = 'Ariana Grande')),
('Scorpion', 'Album Scorpion của Drake', '2018-06-29T00:00:00', 'scorpion.jpg', (SELECT artist_id FROM Artist WHERE artist_name = 'Drake')),
('Purpose', 'Album Purpose của Justin Bieber', '2015-11-13T00:00:00', 'purpose.jpg', (SELECT artist_id FROM Artist WHERE artist_name = 'Justin Bieber'));

-- Bảng Song
INSERT INTO song (song_tittle, release_date, duration, file_path, image, likes, downloads, genre_id) VALUES
('Blank Space', 2014, '00:03:51', 'music/blankspace.mp3', 'blankspace.jpg', 1000, 500, (SELECT genre_id FROM Genre WHERE genre_name = 'Pop')),
('Believer', 2017, '00:03:24', 'believer.mp3', 'believer.jpg', 1500, 700, (SELECT genre_id FROM Genre WHERE genre_name = 'Rock')),
('Closer', 2016, '00:04:04', 'closer.mp3', 'closer.jpg', 2000, 1000, (SELECT genre_id FROM Genre WHERE genre_name = 'Electronic')),
('Shape of You', 2017, '00:03:53', 'shapeofyou.mp3', 'shapeofyou.jpg', 2500, 1200, (SELECT genre_id FROM Genre WHERE genre_name = 'Pop')),
('Hello', 2015, '00:04:55', 'hello.mp3', 'hello.jpg', 3000, 1500, (SELECT genre_id FROM Genre WHERE genre_name = 'Pop')),
('Girls Like You', 2018, '00:04:05', 'girlslikeyou.mp3', 'girlslikeyou.jpg', 3500, 1700, (SELECT genre_id FROM Genre WHERE genre_name = 'Pop')),
('Something Just Like This', 2017, '00:04:07', 'somethingjustlikethis.mp3', 'somethingjustlikethis.jpg', 4000, 2000, (SELECT genre_id FROM Genre WHERE genre_name = 'Electronic')),
('No Tears Left to Cry', 2018, '00:03:25', 'notearslefttocry.mp3', 'notearslefttocry.jpg', 4500, 2200, (SELECT genre_id FROM Genre WHERE genre_name = 'Pop')),
('God s Plan', 2018, '00:03:18', 'godsplan.mp3', 'godsplan.jpg', 5000, 2500, (SELECT genre_id FROM Genre WHERE genre_name = 'Hip-hop')),
('Love Yourself', 2015, '00:03:53', 'loveyourself.mp3', 'loveyourself.jpg', 5500, 2700, (SELECT genre_id FROM Genre WHERE genre_name = 'Pop'));


-- Bảng Playlist
INSERT INTO playlist (playlist_name, create_date, user_id) VALUES
('Nhạc yêu thích', '2023-10-26 14:00:00', (SELECT user.user_id FROM User WHERE username = 'user1')),
('Nhạc thư giãn', '2023-10-27 16:30:00', (SELECT user.user_id FROM User WHERE username = 'user2')),
('Nhạc năng động', '2023-10-28 18:45:00', (SELECT user.user_id FROM User WHERE username = 'user3')),
('Nhạc buồn', '2023-10-29 20:00:00', (SELECT user.user_id FROM User WHERE username = 'user4')),
('Nhạc vui', '2023-10-30 22:30:00', (SELECT user.user_id FROM User WHERE username = 'user5')),
('Nhạc học tập', '2023-10-31 00:45:00', (SELECT user.user_id FROM User WHERE username = 'user6')),
('Nhạc làm việc', '2023-11-01 02:00:00', (SELECT user.user_id FROM User WHERE username = 'user7')),
('Nhạc tập thể dục', '2023-11-02 04:30:00', (SELECT user.user_id FROM User WHERE username = 'user8')),
('Nhạc du lịch', '2023-11-03 06:45:00', (SELECT user.user_id FROM User WHERE username = 'user9')),
('Nhạc giải stress', '2023-11-04 08:00:00', (SELECT user.user_id FROM User WHERE username = 'user10'));

-- Bảng Playlist_Song
INSERT INTO playlist_song (playlist_id, song_id) VALUES
((SELECT playlist_id FROM Playlist WHERE playlist_name = 'Nhạc yêu thích'), (SELECT song.song_id FROM Song WHERE song_tittle = 'Blank Space')),
((SELECT playlist_id FROM Playlist WHERE playlist_name = 'Nhạc thư giãn'), (SELECT song.song_id FROM Song WHERE song_tittle = 'Believer')),
((SELECT playlist_id FROM Playlist WHERE playlist_name = 'Nhạc năng động'), (SELECT song.song_id FROM Song WHERE song_tittle = 'Closer')),
((SELECT playlist_id FROM Playlist WHERE playlist_name = 'Nhạc buồn'), (SELECT song.song_id FROM Song WHERE song_tittle = 'Shape of You')),
((SELECT playlist_id FROM Playlist WHERE playlist_name = 'Nhạc vui'), (SELECT song.song_id FROM Song WHERE song_tittle = 'Hello')),
((SELECT playlist_id FROM Playlist WHERE playlist_name = 'Nhạc học tập'), (SELECT song.song_id FROM Song WHERE song_tittle = 'Girls Like You')),
((SELECT playlist_id FROM Playlist WHERE playlist_name = 'Nhạc làm việc'), (SELECT song.song_id FROM Song WHERE song_tittle = 'Something Just Like This')),
((SELECT playlist_id FROM Playlist WHERE playlist_name = 'Nhạc tập thể dục'), (SELECT song.song_id FROM Song WHERE song_tittle = 'No Tears Left to Cry')),
((SELECT playlist_id FROM Playlist WHERE playlist_name = 'Nhạc du lịch'), (SELECT song.song_id FROM Song WHERE song_tittle = 'God s Plan')),
((SELECT playlist_id FROM Playlist WHERE playlist_name = 'Nhạc giải stress'), (SELECT song.song_id FROM Song WHERE song_tittle = 'Love Yourself'));

-- Bảng Comment
INSERT INTO comment (content, create_date, parent_id, rate, user_id, song_id) VALUES
('Bài hát hay quá!', '2023-10-29 09:00:00', NULL, 5, (SELECT user.user_id FROM User WHERE username = 'user1'), (SELECT song.song_id FROM Song WHERE song_tittle = 'Blank Space')),
('Tôi rất thích bài hát này!', '2023-10-30 11:30:00', NULL, 4, (SELECT user.user_id FROM User WHERE username = 'user2'), (SELECT song.song_id FROM Song WHERE song_tittle = 'Believer')),
('Tuyệt vời!', '2023-10-31 13:45:00', 1, 5, (SELECT user.user_id FROM User WHERE username = 'user3'), (SELECT song.song_id FROM Song WHERE song_tittle = 'Blank Space')),
('Bài hát này thật ý nghĩa!', '2023-11-01 15:00:00', NULL, 3, (SELECT user.user_id FROM User WHERE username = 'user4'), (SELECT song.song_id FROM Song WHERE song_tittle = 'Shape of You')),
('Giai điệu thật bắt tai!', '2023-11-02 17:30:00', NULL, 5, (SELECT user.user_id FROM User WHERE username = 'user5'), (SELECT song.song_id FROM Song WHERE song_tittle = 'Hello')),
('Tôi đã nghe đi nghe lại nhiều lần!', '2023-11-03 19:45:00', NULL, 4, (SELECT user.user_id FROM User WHERE username = 'user6'), (SELECT song.song_id FROM Song WHERE song_tittle = 'Girls Like You')),
('Âm thanh tuyệt vời!', '2023-11-04 21:00:00', 7, 5, (SELECT user.user_id FROM User WHERE username = 'user7'), (SELECT song.song_id FROM Song WHERE song_tittle = 'Something Just Like This')),
('Ca sĩ hát rất hay!', '2023-11-05 23:30:00', NULL, 4, (SELECT user.user_id FROM User WHERE username = 'user8'), (SELECT song.song_id FROM Song WHERE song_tittle = 'No Tears Left to Cry')),
('Lời bài hát sâu sắc!', '2023-11-06 01:45:00', NULL, 3, (SELECT user.user_id FROM User WHERE username = 'user9'), (SELECT song.song_id FROM Song WHERE song_tittle = 'God s Plan')),
('Một bài hát kinh điển!', '2023-11-07 03:00:00', NULL, 5, (SELECT user.user_id FROM User WHERE username = 'user10'), (SELECT song.song_id FROM Song WHERE song_tittle = 'Love Yourself'));


-- Bảng Artist_Song
INSERT INTO artist_song (artist_id, song_id) VALUES
((SELECT artist_id FROM Artist WHERE artist_name = 'Taylor Swift'), (SELECT song.song_id FROM Song WHERE song_tittle = 'Blank Space')),
((SELECT artist_id FROM Artist WHERE artist_name = 'Imagine Dragons'), (SELECT song.song_id FROM Song WHERE song_tittle = 'Believer')),
((SELECT artist_id FROM Artist WHERE artist_name = 'The Chainsmokers'), (SELECT song.song_id FROM Song WHERE song_tittle = 'Closer')),
((SELECT artist_id FROM Artist WHERE artist_name = 'Ed Sheeran'), (SELECT song.song_id FROM Song WHERE song_tittle = 'Shape of You')),
((SELECT artist_id FROM Artist WHERE artist_name = 'Adele'), (SELECT song.song_id FROM Song WHERE song_tittle = 'Hello')),
((SELECT artist_id FROM Artist WHERE artist_name = 'Maroon 5'), (SELECT song.song_id FROM Song WHERE song_tittle = 'Girls Like You')),
((SELECT artist_id FROM Artist WHERE artist_name = 'Coldplay'), (SELECT song.song_id FROM Song WHERE song_tittle = 'Something Just Like This')),
((SELECT artist_id FROM Artist WHERE artist_name = 'Ariana Grande'), (SELECT song.song_id FROM Song WHERE song_tittle = 'No Tears Left to Cry')),
((SELECT artist_id FROM Artist WHERE artist_name = 'Drake'), (SELECT song.song_id FROM Song WHERE song_tittle = 'God s Plan')),
((SELECT artist_id FROM Artist WHERE artist_name = 'Justin Bieber'), (SELECT song.song_id FROM Song WHERE song_tittle = 'Love Yourself'));

-- Bảng Menu
INSERT INTO menu (link, title, order_list) VALUES
                                          ('/home', 'Trang chủ', 1),
                                          ('/songs', 'Bài hát', 2),
                                          ('/playlists', 'Playlist', 3),
                                          ('/artists', 'Nghệ sĩ', 4),
                                          ('/albums', 'Album', 5),
                                          ('/genres', 'Thể loại', 6),
                                          ('/search', 'Tìm kiếm', 7),
                                          ('/upload', 'Tải lên', 8),
                                          ('/profile', 'Hồ sơ', 9),
                                          ('/settings', 'Cài đặt', 10);

-- Bảng InvalidatedToken
INSERT INTO invalidated_token (id, expiry_time) VALUES
('oldtoken123', '2023-10-26 10:00:00'),
('expiredtoken456', '2023-10-27 12:00:00'),
('invalidtoken789', '2023-10-28 14:00:00'),
('badtokenabc', '2023-10-29 16:00:00'),
('dummytokendef', '2023-10-30 18:00:00'),
('testtokenghi', '2023-10-31 20:00:00'),
('faketokenjkl', '2023-11-01 22:00:00'),
('mocktokenmno', '2023-11-02 00:00:00'),
('sampletokenpqr', '2023-11-03 02:00:00'),
('randomtokenstu', '2023-11-04 04:00:00');