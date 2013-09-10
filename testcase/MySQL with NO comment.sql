/*
MySQL Data Transfer
Source Host: test
Source Database: xx
Target Host: test
Target Database: xx
Date: 2007-4-23 17:57:20
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for diesel_check_note
-- ----------------------------
CREATE TABLE `diesel_check_note` (
  `id` int(10) unsigned NOT NULL,
  `colour` double default NULL,
  `colour_str` varchar(45) default NULL,
  `oxidation_stability` int(11) default NULL,
  `sulphur_contentPct` double default NULL,
  `water_content_pct` varchar(45) default NULL,
  `acidity` double default NULL,
  `ash_content_pct` double default NULL,
  `copper_corrosion` double default NULL,
  `has_mechanical_admixture` int(11) default NULL,
  `kinematic_viscosity` double default NULL,
  `freezing_point` double default NULL,
  `condensation_point` double default NULL,
  `flash_point` double default NULL,
  `cetane_number` double default NULL,
  `distilling_tpct50` double default NULL,
  `distilling_tpct90` double default NULL,
  `distilling_tpct95` double default NULL,
  `std_density` double default NULL,
  `distillationResidueCarbonPct` double default NULL,
  `colourNote` varchar(45) default NULL,
  `oxidationStabilityNote` varchar(45) default NULL,
  `sulphurContentPctNote` varchar(45) default NULL,
  `waterContentPctNote` varchar(45) default NULL,
  `acidityNote` varchar(45) default NULL,
  `ashContentPctNote` varchar(45) default NULL,
  `copperCorrosionNote` varchar(45) default NULL,
  `hasMechanicalAdmixtureNote` varchar(45) default NULL,
  `kinematicViscosityNote` varchar(45) default NULL,
  `freezingPointNote` varchar(45) default NULL,
  `condensationPointNote` varchar(45) default NULL,
  `flashPointNote` varchar(45) default NULL,
  `cetaneNumberNote` varchar(45) default NULL,
  `distillingTpct50Note` varchar(45) default NULL,
  `distillingTpct90Note` varchar(45) default NULL,
  `distillingTpct95Note` varchar(45) default NULL,
  `stdDensityNote` varchar(45) default NULL,
  `distillationResidueCarbonNote` varchar(45) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;