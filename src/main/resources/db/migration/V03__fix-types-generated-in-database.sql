ALTER TABLE public.channel DROP COLUMN active_membership;
ALTER TABLE public.channel ADD active_membership bool DEFAULT false;

ALTER TABLE public.channel DROP COLUMN joined;
ALTER TABLE public.channel ADD joined timestamp;
