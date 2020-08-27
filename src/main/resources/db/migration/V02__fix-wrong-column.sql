ALTER TABLE public.video DROP COLUMN published;
ALTER TABLE public.video ADD published timestamp NULL;

