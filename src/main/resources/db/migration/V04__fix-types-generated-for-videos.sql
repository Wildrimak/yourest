ALTER TABLE public.video DROP COLUMN is_visible_reactions;
ALTER TABLE public.video ADD is_visible_reactions bool DEFAULT true;
