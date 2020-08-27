
CREATE SEQUENCE public.users_id_user_seq;

CREATE TABLE public.users (
                id_user INTEGER NOT NULL DEFAULT nextval('public.users_id_user_seq'),
                name VARCHAR NOT NULL,
                photo VARCHAR,
                email VARCHAR NOT NULL,
                password VARCHAR NOT NULL,
                CONSTRAINT id_user PRIMARY KEY (id_user)
);


ALTER SEQUENCE public.users_id_user_seq OWNED BY public.users.id_user;

CREATE SEQUENCE public.channel_id_channel_seq;

CREATE TABLE public.channel (
                id_channel INTEGER NOT NULL DEFAULT nextval('public.channel_id_channel_seq'),
                id_user INTEGER NOT NULL,
                name VARCHAR NOT NULL,
                description VARCHAR,
                joined DATE NOT NULL,
                location VARCHAR,
                art VARCHAR,
                views BIGINT NOT NULL,
                active_membership BIT NOT NULL,
                CONSTRAINT id_channel PRIMARY KEY (id_channel)
);


ALTER SEQUENCE public.channel_id_channel_seq OWNED BY public.channel.id_channel;

CREATE SEQUENCE public.subscriptions_id_subscription_seq;

CREATE TABLE public.subscriptions (
                id_subscription INTEGER NOT NULL DEFAULT nextval('public.subscriptions_id_subscription_seq'),
                id_my_channel INTEGER NOT NULL,
                id_other_channel INTEGER NOT NULL,
                bell_status VARCHAR NOT NULL,
                is_membership BIT NOT NULL,
                CONSTRAINT id_subscription PRIMARY KEY (id_subscription)
);


ALTER SEQUENCE public.subscriptions_id_subscription_seq OWNED BY public.subscriptions.id_subscription;

CREATE SEQUENCE public.video_id_video_seq;

CREATE TABLE public.video (
                id_video INTEGER NOT NULL DEFAULT nextval('public.video_id_video_seq'),
                id_channel INTEGER NOT NULL,
                title VARCHAR NOT NULL,
                description VARCHAR,
                views BIGINT NOT NULL,
                likes BIGINT NOT NULL,
                deslikes BIGINT NOT NULL,
                is_visible_reactions BIT NOT NULL,
                duration_seconds INTEGER NOT NULL,
                content_url VARCHAR NOT NULL,
                published VARCHAR NOT NULL,
                CONSTRAINT id_video PRIMARY KEY (id_video)
);


ALTER SEQUENCE public.video_id_video_seq OWNED BY public.video.id_video;

CREATE SEQUENCE public.comment_video_id_comment_seq;

CREATE TABLE public.comment_video (
                id_comment INTEGER NOT NULL DEFAULT nextval('public.comment_video_id_comment_seq'),
                id_video INTEGER NOT NULL,
                reply_id_comment INTEGER NOT NULL,
                text VARCHAR NOT NULL,
                likes BIGINT NOT NULL,
                reaction_status VARCHAR NOT NULL,
                CONSTRAINT id_comment PRIMARY KEY (id_comment)
);


ALTER SEQUENCE public.comment_video_id_comment_seq OWNED BY public.comment_video.id_comment;

ALTER TABLE public.channel ADD CONSTRAINT user_account_fk
FOREIGN KEY (id_user)
REFERENCES public.users (id_user)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE public.video ADD CONSTRAINT account_video_fk
FOREIGN KEY (id_channel)
REFERENCES public.channel (id_channel)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE public.subscriptions ADD CONSTRAINT channel_subscriptions_fk
FOREIGN KEY (id_other_channel)
REFERENCES public.channel (id_channel)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE public.subscriptions ADD CONSTRAINT channel_subscriptions_fk1
FOREIGN KEY (id_my_channel)
REFERENCES public.channel (id_channel)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE public.comment_video ADD CONSTRAINT video_comments_fk
FOREIGN KEY (id_video)
REFERENCES public.video (id_video)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE public.comment_video ADD CONSTRAINT comments_comments_fk
FOREIGN KEY (reply_id_comment)
REFERENCES public.comment_video (id_comment)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;
